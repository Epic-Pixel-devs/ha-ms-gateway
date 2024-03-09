package com.br.ha.ms.gateway.config;

import java.util.List;
import java.util.function.Predicate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

@Service
public class RouteValidatorConfig {

  public Predicate<ServerHttpRequest> isSecured = request -> List.of("/auth/register").stream()
      .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
