package com.br.ha.ms.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

  @Value("${base.service.uri}")
  private String uri;

  @Value("${base.service.name}")
  private String serviceName;

  @Value("${base.service.context-path}")
  private String contextPath;

  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    return builder.routes()
        .route(serviceName, r -> r.path(contextPath).uri(uri))
        .build();
  }
}
