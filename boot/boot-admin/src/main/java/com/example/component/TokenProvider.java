package com.example.component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider {

    @Value("${}")
    private final String accessSecretKey = "temp";
    @Value("${}")
    private final String refreshSecretKey = "temp";
    @Value("${}")
    private final long accessExpirationHours = 10;
    @Value("${}")
    private final long refreshExpirationHours = 7;
    @Value("${}")
    private final String issuer = "temp";

    public String createAccessToken(String userSpecification) {
        return Jwts.builder()
            .signWith(
                new SecretKeySpec(accessSecretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName()))
            .setSubject(userSpecification)
            .setIssuer(issuer)
            .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
            .setExpiration(Date.from(Instant.now().plus(accessExpirationHours, ChronoUnit.HOURS)))
            .compact();
    }

    public String createRefreshToken(String userSpecification) {
        return Jwts.builder()
            .signWith(
                new SecretKeySpec(refreshSecretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName()))
            .setSubject(userSpecification)
            .setIssuer(issuer)
            .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
            .setExpiration(Date.from(Instant.now().plus(refreshExpirationHours, ChronoUnit.DAYS)))
            .compact();
    }

    public String validateAccessTokenAndGetSubject(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(accessSecretKey.getBytes())
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public String validateRefreshTokenAndGetSubject(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(refreshSecretKey.getBytes())
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }
}
