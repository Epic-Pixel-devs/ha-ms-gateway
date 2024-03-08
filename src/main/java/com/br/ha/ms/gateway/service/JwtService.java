package com.br.ha.ms.gateway.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  @Value("${jwt.secret}")
  private String secret;

  public Claims getClaims(String token) {
    Key key = Keys.hmacShaKeyFor(secret.getBytes());

    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJwt(token).getBody();
  }

  public boolean isExpired(String token) {
    return getClaims(token).getExpiration().before(new Date());
  }
}
