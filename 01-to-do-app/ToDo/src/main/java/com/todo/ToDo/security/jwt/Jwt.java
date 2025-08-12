package com.todo.ToDo.security.jwt;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.todo.ToDo.models.UserModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Jwt {

    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UserModel userModel) {
        Instant nowTime = Instant.now();
        Instant expirationTime = nowTime.plusSeconds(3600);

        Date issueAt = Date.from(nowTime);
        Date expirationJwt = Date.from(expirationTime);

        //guardamos los diferentes claims para el jwt
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userModel.getUsername());
        claims.put("rol", userModel.getRol());

        return Jwts.builder()
                .claims(claims)
                .subject(String.valueOf(userModel.getId()))
                .issuedAt(issueAt)
                .expiration(expirationJwt)
                .signWith(key)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        try {
            JwtParser parser = Jwts.parser().verifyWith(this.key).build();
            return parser.parseSignedClaims(token).getPayload();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String extractUserId(String token) {
        return extractAllClaims(token).getSubject();
    }

    public String extractUsername(String token){
        return extractAllClaims(token).get("username", String.class);
    }

    public String extractRol(String token) {
        return extractAllClaims(token).get("rol", String.class);
    }
    
    public boolean isValidToken(UserDetails user, String token) {
        final String subjectJwt = extractAllClaims(token).getSubject();
        Long user_id = ((UserModel) user).getId();
        return (subjectJwt.equals(String.valueOf(user_id)) && (!isExpiredToken(token)));
    }

    public boolean isExpiredToken(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}
