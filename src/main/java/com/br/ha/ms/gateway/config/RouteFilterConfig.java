package com.br.ha.ms.gateway.config;

import com.br.ha.ms.gateway.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private final Logger log = LoggerFactory.getLogger(RouteFilterConfig.class);

  @Autowired
  private JwtService jwtService;

  @Autowired
  private RouteValidatorConfig routeValidatorConfig;


  /**
   * Responsible to filter Http Request
   *
   * @param exchange ServerWebExchange
   * @param chain    GatewayFilterChain
   * @return Mono<Void>
   */
  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    String prefix = "[RouteFilterConfig] (filter): ";

    log.info("{} request server http", prefix);
    ServerHttpRequest request = exchange.getRequest();

    if (routeValidatorConfig.isSecured.test(request)) {

      log.info("{} zone secured validate", prefix);
      if (authMissing(request)) {
        return onError(exchange);
      }

      log.info("{} get token header", prefix);
      final String token = request.getHeaders().getOrEmpty(AUTHORIZATION).get(0);

      try {
        // TODO: implement others validation with information from token
        if (jwtService.isExpired(token)) {
          log.error("{} error decode token expired {}", prefix, token);
          return onError(exchange);
        }
      } catch (Exception e) {
        log.error("{} error to validate token jwt {}", prefix, e.getMessage());
        return onError(exchange);
      }
    }
    return chain.filter(exchange);
  }

  /**
   * Responsible to validate if exists Authorization key on Http Header
   *
   * @param request ServerHttpRequest
   * @return boolean
   */
  private boolean authMissing(ServerHttpRequest request) {
    return !request.getHeaders().containsKey(AUTHORIZATION);
  }

  /**
   * Responsible to return Http status UNAUTHORIZED
   *
   * @param exchange ServerWebExchange
   * @return Mono<Void>
   */
  private Mono<Void> onError(ServerWebExchange exchange) {
    ServerHttpResponse response = exchange.getResponse();
    response.setStatusCode(HttpStatus.UNAUTHORIZED);

    return response.setComplete();
  }
}
