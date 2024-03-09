package com.br.ha.ms.gateway.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private String expiration;
  private Key key;

  /**
   * Responsible to initialize with values java security key
   */
  @PostConstruct
  public void init() {
    key = Keys.hmacShaKeyFor(secret.getBytes());
  }


  /**
   * Responsible to decode JWT
   *
   * @param token string
   * @return Claims JWT
   */
  public Claims getClaims(String token) {
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
  }

  /**
   * Responsible to validate if date is after than today
   *
   * @param token string
   * @return boolean
   */
  public boolean isExpired(String token) {
    return getClaims(token).getExpiration().before(new Date());
  }
}
