package org.example.expensemanager.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

  @Value("${jwt.secret}")
  private String secret;

  private static final long EXPIRATION = 1000 * 60 * 60 * 24; // 24h

  private SecretKey getKey() {
    return Keys.hmacShaKeyFor(secret.getBytes());
  }

  public String generateToken(UserDetails user) {
    return Jwts.builder()
      .subject(user.getUsername())
      .issuedAt(new Date())
      .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
      .signWith(getKey())
      .compact();
  }

  public String extractEmail(String token) {
    return Jwts.parser()
      .verifyWith(getKey())
      .build()
      .parseSignedClaims(token)
      .getPayload()
      .getSubject();
  }

  public boolean isValid(String token, UserDetails user) {
    return extractEmail(token).equals(user.getUsername())
      && !isExpired(token);
  }

  private boolean isExpired(String token) {
    return Jwts.parser()
      .verifyWith(getKey())
      .build()
      .parseSignedClaims(token)
      .getPayload()
      .getExpiration()
      .before(new Date());
  }
}
