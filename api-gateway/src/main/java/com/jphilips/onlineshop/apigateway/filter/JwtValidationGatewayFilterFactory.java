package com.jphilips.onlineshop.apigateway.filter;

import com.jphilips.onlineshop.apigateway.config.RoleBasedAccessConfig;
import com.jphilips.onlineshop.shared.dto.AuthDetailsDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JwtValidationGatewayFilterFactory extends AbstractGatewayFilterFactory<RoleBasedAccessConfig> {
    private final WebClient webClient;

    public JwtValidationGatewayFilterFactory(WebClient.Builder webClientBuilder,
                                             @Value("${AUTH_SERVICE_URI}") String authServiceUrl) {
        super(RoleBasedAccessConfig.class);
        this.webClient = webClientBuilder.baseUrl(authServiceUrl).build();
    }

    @Override
    public GatewayFilter apply(RoleBasedAccessConfig config) {
        return (exchange, chain) -> {
            String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            return webClient.get()
                    .uri("/auth/validate")
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .retrieve()
                    .bodyToMono(AuthDetailsDTO.class)
                    .flatMap(userDetails -> {
                        List<String> userRoles = userDetails.roles();

                        // Role Check
                        boolean authorized = userRoles.stream()
                                .anyMatch(role -> config.getRoles().stream()
                                .map(String::trim)
                                .map(String::toUpperCase)
                                .anyMatch(r -> r.equals(role.toUpperCase())));

                        if (!authorized) {
                            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                            return exchange.getResponse().setComplete();
                        }

                        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                                .headers(httpHeaders -> {
                                    httpHeaders.remove(HttpHeaders.AUTHORIZATION);
                                    httpHeaders.remove("X-User-Id");
                                    httpHeaders.remove("X-User-Email");
                                    httpHeaders.remove("X-User-Name");
                                })
                                .header("X-User-Id", userDetails.id().toString())
                                .header("X-User-Email", userDetails.email())
                                .header("X-User-Name", userDetails.name())
                                .build();

                        ServerWebExchange mutatedExchange = exchange.mutate()
                                .request(mutatedRequest)
                                .build();

                        return chain.filter(mutatedExchange);
                    })
                    .onErrorResume(error -> {
                        if (error instanceof WebClientResponseException ex) {
                            exchange.getResponse().setStatusCode(ex.getStatusCode());

                            // Set content-type and return the structured body as-is
                            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
                            DataBuffer buffer = exchange.getResponse()
                                    .bufferFactory()
                                    .wrap(ex.getResponseBodyAsByteArray());

                            return exchange.getResponse().writeWith(Mono.just(buffer));
                        }

                        // Fallback: generic 401
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    });
        };
    }
}
