package com.br.ha.ms.gateway.config;

import com.br.ha.ms.gateway.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@RefreshScope
@Component
public class RouteFilterConfig implements GatewayFilter {

  private final String AUTHORIZATION = "Authorization";
  @Autowired
  private JwtService jwtService;

  @Autowired
  private RouteValidatorConfig routeValidatorConfig;


  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    ServerHttpRequest request = exchange.getRequest();

    if (routeValidatorConfig.isSecured.test(request)) {
      if (authMissing(request)) {
        return onError(exchange, HttpStatus.UNAUTHORIZED);
      }

      final String token = request.getHeaders().getOrEmpty(AUTHORIZATION).get(0);

      if (jwtService.isExpired(token)) {
        return onError(exchange, HttpStatus.UNAUTHORIZED);
      }
    }

    return chain.filter(exchange);
  }

  private boolean authMissing(ServerHttpRequest request) {
    return !request.getHeaders().containsKey(AUTHORIZATION);
  }

  private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
    ServerHttpResponse response = exchange.getResponse();
    response.setStatusCode(httpStatus);

    return response.setComplete();
  }
}
