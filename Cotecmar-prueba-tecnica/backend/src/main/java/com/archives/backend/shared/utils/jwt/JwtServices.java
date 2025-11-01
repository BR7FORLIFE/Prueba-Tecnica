package com.archives.backend.shared.utils.jwt;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class JwtServices {

    @Value("${jwt.secret}")
    private String secret_firm;

    private SecretKey secret_key;

    @PostConstruct
    public void init() {
        this.secret_key = Keys.hmacShaKeyFor(secret_firm.getBytes(StandardCharsets.UTF_8));
    }

    public String generateJwt(UserDetails user) {
        Instant timestampIssueAt = Instant.now();
        Instant timestampExpiration = timestampIssueAt.plusSeconds(3600);

        Date issueAt = Date.from(timestampIssueAt);
        Date expiration = Date.from(timestampExpiration);

        Map<String, Object> claims = new HashMap<>();
        claims.put("rols", user.getAuthorities());

        return Jwts
                .builder()
                .subject(user.getUsername())
                .claims(claims)
                .signWith(this.secret_key)
                .issuedAt(issueAt)
                .expiration(expiration)
                .compact();
    }

    private Claims extractAllClaims(String token) {
        try {
            JwtParser parser = Jwts.parser().verifyWith(this.secret_key).build();
            return parser.parseSignedClaims(token).getPayload();

        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtException("Error to extract claims in jwt!");
        }
    }

    public String extractUsername(String token) throws Exception {
        try {
            String username = (String) extractAllClaims(token).get("username");
            return username;
        } catch (Exception e) {
            throw new Exception("Error to get username of jwt!");
        }
    }

    public boolean isExpiredjwt(String token) {
        return extractAllClaims(token).getExpiration().after(new Date());
    }
}
