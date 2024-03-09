package com.br.ha.ms.gateway.config;

import java.util.function.Predicate;
import java.util.stream.Stream;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

@Service
public class RouteValidatorConfig {

  /**
   * Responsible to filter path - create whitelist to permit access
   */
  public Predicate<ServerHttpRequest> isSecured = request -> Stream.of("/auth/register")
      .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
