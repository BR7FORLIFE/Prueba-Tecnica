package com.files.__airplane_agendant.security.jwt;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.files.__airplane_agendant.Dtos.users.AuthenticationRequest;
import com.files.__airplane_agendant.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Jwt {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(User request) {
        Instant now = Instant.now();
        Instant expiration = now.plusSeconds(3600);

        Date nowValue = Date.from(now);
        Date expirationValue = Date.from(expiration);

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", request.getUsername());

        return Jwts.builder()
                .claims(claims)
                .subject(String.valueOf(request.getId()))
                .issuedAt(nowValue)
                .expiration(expirationValue)
                .signWith(key)
                .compact();
    }

    public Claims extractInfoJwt(String token) {
        try {
            JwtParser parser = Jwts.parser().verifyWith(this.key).build();
            return parser.parseSignedClaims(token).getPayload();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUsername(String token) {
        return extractInfoJwt(token).get("username", String.class);
    }

    public boolean isValidToken(UserDetails user, String token) {
        final String idJwt = extractInfoJwt(token).getSubject();
        UUID idUserDetails = ((AuthenticationRequest) user).getId();

        return (idJwt.equals(String.valueOf(idUserDetails)) && !expirationToken(token));
    }

    public boolean expirationToken(String token) {
        return extractInfoJwt(token).getExpiration().before(new Date());
    }
}
