package com.healthpro.apigateway.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Component
public class JwtValidationGatewayFilterFactory extends
        AbstractGatewayFilterFactory<Object> {

    private final WebClient webClient;

    public JwtValidationGatewayFilterFactory(WebClient.Builder webClientBuilder
        , @Value("${auth.service.url}") String authServiceUrl) {
        this.webClient = webClientBuilder.baseUrl(authServiceUrl).build();
    }

//    @Override
//    public GatewayFilter apply(Object config) {
//        return  (exchange, chain) -> {
//            HttpCookie jwtCookie = exchange.getRequest().getCookies().getFirst("jwt");
//
//            if (jwtCookie == null) {
//                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                return exchange.getResponse().setComplete();
//            }
//            String token = jwtCookie.getValue();
//
//            return webClient.get()
//                    .uri("/api/v1/auth/validate")
//                    .cookie("jwt", token)
//                    .retrieve()
//                    .toBodilessEntity()
//                    .then(chain.filter(exchange));
//        };
//    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            HttpCookie jwtCookie = exchange.getRequest().getCookies().getFirst("jwt");

            if (jwtCookie == null) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            String token = jwtCookie.getValue();

            return webClient.get()
                    .uri("/api/v1/auth/validate")
                    .cookie("jwt", token)
                    .retrieve()
                    .bodyToMono(UserInfoResponse.class)
                    .flatMap(userInfo -> {
                        var mutatedExchange = exchange.mutate()
                                .request(r -> r.headers(headers -> {
                                    headers.add("X-User-Id", userInfo.getId().toString());
                                    headers.add("X-UserRole-Id", userInfo.getUserRoleId().toString());
                                    headers.add("X-User-Email", userInfo.getEmail());
                                    headers.add("X-User-Role", userInfo.getRole());
                                }))
                                .build();
                        return chain.filter(mutatedExchange);
                    })
                    .onErrorResume(e -> {
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    });
        };
    }

    private static class UserInfoResponse {
        private UUID id;
        private UUID userRoleId;
        private String email;
        private String role;

        public UUID getId() {
            return id;
        }
        public void setId(UUID id) {
            this.id = id;
        }

        public UUID getUserRoleId() {
            return userRoleId;
        }
        public void setUserRoleId(UUID userRoleId) {
            this.userRoleId = userRoleId;
        }

        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }

        public String getRole() {
            return role;
        }
        public void setRole(String role) {
            this.role = role;
        }
    }
}
