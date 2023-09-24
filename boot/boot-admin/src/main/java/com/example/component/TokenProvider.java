package com.example.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application-token.yml")
public class TokenProvider {

    private final String accessSecretKey;
    private final String refreshSecretKey;
    private final long accessExpirationHours;
    private final long refreshExpirationHours;
    private final String issuer;

    public TokenProvider(
        @Value("${access-secret-key}") String accessSecretKey,
        @Value("${refresh-secret-key}") String refreshSecretKey,
        @Value("${access-expiration-hours}") long accessExpirationHours,
        @Value("${refresh-expiration-hours}") long refreshExpirationHours,
        @Value("${issuer}") String issuer
    ) {
        this.accessSecretKey = accessSecretKey;
        this.refreshSecretKey = refreshSecretKey;
        this.accessExpirationHours = accessExpirationHours;
        this.refreshExpirationHours = refreshExpirationHours;
        this.issuer = issuer;
    }

    public String createAccessToken(String userSpecification) {
        return Jwts.builder()
            .signWith(
                new SecretKeySpec(accessSecretKey.getBytes(),
                    SignatureAlgorithm.HS512.getJcaName()))
            .setSubject(userSpecification)
            .setIssuer(issuer)
            .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
            .setExpiration(Date.from(Instant.now().plus(accessExpirationHours, ChronoUnit.HOURS)))
            .compact();
    }

    public String recreateAccessTokenWithRefreshToken(String refreshToken) {
        String subject = validateRefreshTokenAndGetSubject(refreshToken);
        return createAccessToken(subject);
    }

    public String createRefreshToken(String userSpecification) {
        return Jwts.builder()
            .signWith(
                new SecretKeySpec(refreshSecretKey.getBytes(),
                    SignatureAlgorithm.HS512.getJcaName()))
            .setSubject(userSpecification)
            .setIssuer(issuer)
            .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
            .setExpiration(Date.from(Instant.now().plus(refreshExpirationHours, ChronoUnit.DAYS)))
            .compact();
    }

    public String validateAccessTokenAndGetSubject(String token) {
        return getClaimsJws(accessSecretKey, token)
            .getBody()
            .getSubject();
    }

    private String validateRefreshTokenAndGetSubject(String token) {
        return getClaimsJws(refreshSecretKey, token)
            .getBody()
            .getSubject();
    }

    private Jws<Claims> getClaimsJws(String accessSecretKey, String token) {
        return Jwts.parserBuilder()
            .setSigningKey(accessSecretKey.getBytes())
            .build()
            .parseClaimsJws(token);
    }
}
