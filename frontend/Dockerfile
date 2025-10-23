#FROM node:20-alpine AS builder
#
#WORKDIR /app
#
#COPY package.json package-lock.json* pnpm-lock.yaml* yarn.lock* ./
#
#RUN if [ -f pnpm-lock.yaml ]; then npm install -g pnpm && pnpm install; \
#    elif [ -f yarn.lock ]; then yarn install; \
#    else npm install; fi
#
#COPY . .
#
#RUN npm run build
#
#FROM node:20-alpine AS runner
#
#WORKDIR /app
#
#COPY --from=builder /app/package.json ./
#COPY --from=builder /app/.next ./.next
#COPY --from=builder /app/public ./public
#COPY --from=builder /app/next.config.js ./
#COPY --from=builder /app/node_modules ./node_modules
#
#EXPOSE 3000
#
#CMD ["npm", "run", "start"]

FROM node:20-alpine
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
EXPOSE 3000
CMD ["npm", "run", "dev"]

