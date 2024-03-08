package com.br.ha.ms.gateway.config;

import java.util.Arrays;
import java.util.function.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class RouteValidatorConfig {

  private static final String DELIMITER = ",";
  @Value("${route.open.endpoint}")
  private String routeOpenEndpoint;

  public Predicate<ServerHttpRequest> isSecured = request -> Arrays.stream(
          StringUtils.split(routeOpenEndpoint, DELIMITER))
      .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
