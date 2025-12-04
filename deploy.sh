set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR"

echo "=========================================="
echo "healthpro Minikube Deployment Script"
echo "=========================================="

# Colors
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m'

print_info() {
    echo -e "${GREEN}[INFO]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_step() {
    echo -e "${BLUE}==>${NC} $1"
}

print_step "Checking prerequisites..."

if ! command -v minikube &> /dev/null; then
    print_error "Minikube is not installed!"
    echo "Install: https://minikube.sigs.k8s.io/docs/start/"
    exit 1
fi

if ! command -v kubectl &> /dev/null; then
    print_error "kubectl is not installed!"
    exit 1
fi

if ! command -v docker &> /dev/null; then
    print_error "docker is not installed!"
    exit 1
fi

if ! minikube status &> /dev/null; then
    print_warning "Minikube is not running. Starting Minikube..."
    minikube start --cpus=4 --memory=8192 --disk-size=20g --driver=docker

    print_info "Enabling required addons..."
    minikube addons enable ingress
    minikube addons enable metrics-server
else
    print_info "Minikube is already running"
fi

print_step "Configuring Docker to use Minikube's Docker daemon..."
eval $(minikube docker-env)
print_info "Docker configured successfully"

print_step "Building Docker images..."

print_info "Building auth-service..."
docker build -t healthpro/auth-service:latest "$SCRIPT_DIR/backend/auth-service" || {
    print_error "Failed to build auth-service"
    exit 1
}

print_info "Building clinic-service..."
docker build -t healthpro/clinic-service:latest "$SCRIPT_DIR/backend/clinic-service" || {
    print_error "Failed to build clinic-service"
    exit 1
}

print_info "Building schedule-service..."
docker build -t healthpro/schedule-service:latest "$SCRIPT_DIR/backend/schedule-service" || {
    print_error "Failed to build schedule-service"
    exit 1
}

print_info "Building api-gateway..."
docker build -t healthpro/api-gateway:latest "$SCRIPT_DIR/backend/api-gateway" || {
    print_error "Failed to build api-gateway"
    exit 1
}

#print_info "Building frontend..."
#docker build -t healthpro/frontend:latest "$SCRIPT_DIR/frontend" || {
#    print_error "Failed to build frontend"
#    exit 1
#}

print_info "All Docker images built successfully!"

print_info "Verifying images..."
docker images | grep healthpro || print_warning "No healthpro images found!"

print_step "Deploying to Kubernetes..."

print_info "Creating namespace..."
kubectl apply -f "$SCRIPT_DIR/k8s/namespace.yml"

print_info "Creating ConfigMaps..."
kubectl apply -f "$SCRIPT_DIR/k8s/configs/configmap.yml"

print_info "Creating Secrets..."
kubectl apply -f "$SCRIPT_DIR/k8s/configs/secrets.yml"

print_info "Deploying PostgreSQL databases..."
kubectl apply -f "$SCRIPT_DIR/k8s/databases/auth-service-db.yml"
kubectl apply -f "$SCRIPT_DIR/k8s/databases/clinic-service-db.yml"
kubectl apply -f "$SCRIPT_DIR/k8s/databases/schedule-service-db.yml"

print_info "Waiting for databases to be ready (30s)..."
sleep 30

print_info "Waiting for StatefulSets to be ready..."
kubectl wait --for=condition=ready pod -l app=auth-service-db -n healthpro --timeout=300s || print_warning "auth-service-db not ready yet"
kubectl wait --for=condition=ready pod -l app=clinic-service-db -n healthpro --timeout=300s || print_warning "clinic-service-db not ready yet"
kubectl wait --for=condition=ready pod -l app=schedule-service-db -n healthpro --timeout=300s || print_warning "schedule-service-db not ready yet"

print_info "Deploying microservices..."
kubectl apply -f "$SCRIPT_DIR/k8s/services/auth-service.yml"
kubectl apply -f "$SCRIPT_DIR/k8s/services/clinic-service.yml"
kubectl apply -f "$SCRIPT_DIR/k8s/services/schedule-service.yml"
kubectl apply -f "$SCRIPT_DIR/k8s/deployments/auth-service.yml"
kubectl apply -f "$SCRIPT_DIR/k8s/deployments/clinic-service.yml"
kubectl apply -f "$SCRIPT_DIR/k8s/deployments/schedule-service.yml"

print_info "Waiting for microservices to be ready (30s)..."
sleep 30

print_info "Deploying API Gateway..."
kubectl apply -f "$SCRIPT_DIR/k8s/services/api-gateway.yml"
kubectl apply -f "$SCRIPT_DIR/k8s/deployments/api-gateway.yml"

#print_info "Deploying Frontend..."
#kubectl apply -f "$SCRIPT_DIR/k8s/services/frontend.yml"
#kubectl apply -f "$SCRIPT_DIR/k8s/deployments/frontend.yml"

print_info "Creating Ingress..."
kubectl apply -f "$SCRIPT_DIR/k8s/ingress/ingress.yml"

#print_info "Creating Horizontal Pod Autoscalers..."
#kubectl apply -f "$SCRIPT_DIR/k8s/11-hpa.yml" || print_warning "HPA might need metrics-server to be fully functional"

print_info "Deployment completed!"

print_step "Deployment Status"
echo ""
kubectl get pods -n healthpro
echo ""
kubectl get svc -n healthpro

MINIKUBE_IP=$(minikube ip)

echo ""
print_step "Access Information"
echo ""
print_info "Minikube IP: ${MINIKUBE_IP}"
echo ""
print_info "Option 1: Port Forward (Recommended for development)"
echo "  Terminal: kubectl port-forward -n healthpro svc/api-gateway 4004:4004"
echo "  Access API: http://localhost:4004"
echo ""
print_info "Option 2: NodePort"
echo "  API Gateway URL: $(minikube service api-gateway -n healthpro --url 2>/dev/null || echo 'Run: minikube service api-gateway -n healthpro --url')"
echo ""
print_info "Option 3: Minikube Tunnel (Requires sudo, run in separate terminal)"
echo "  Run: minikube tunnel"
echo "  Then access via LoadBalancer IPs shown in 'kubectl get svc -n healthpro'"
echo ""
print_info "Option 4: Ingress (after adding to /etc/hosts)"
echo "  Add to /etc/hosts: ${MINIKUBE_IP} healthpro.local"
echo "  Access: http://healthpro.local"
echo ""
print_step "Useful Commands"
echo ""
echo "  View logs:        kubectl logs -f -n healthpro deployment/auth-service"
echo "  View all pods:    kubectl get pods -n healthpro"
echo "  Describe pod:     kubectl describe pod -n healthpro <pod-name>"
echo "  Dashboard:        minikube dashboard"
echo "  Cleanup:          ./cleanup-minikube.sh"
echo ""
print_info "Deployment completed successfully!"
echo "=========================================="
