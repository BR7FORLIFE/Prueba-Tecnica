package com.files.__airplane_agendant.security.jwt;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.files.__airplane_agendant.Dtos.users.AuthenticationRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Jwt {
    
    @Value("${jwt.secret}")
    private final String SECRET_KEY;

    private SecretKey key;

    public void init(){
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }
    
    public String generateToken(AuthenticationRequest request){
        Instant now = Instant.now();
        Instant expiration = now.plusSeconds(3600);

        Date nowValue = Date.from(now);
        Date expirationValue = Date.from(expiration);

        Map<String,AuthenticationRequest> claims = new HashMap<>();
        claims.put("user_info", request);
    
        return Jwts.builder()
                .claims(claims)
                .subject(String.valueOf(request.getId()))
                .issuedAt(nowValue)
                .expiration(expirationValue)
                .signWith(key)
                .compact();
    }

    public Claims extractInfoJwt(String token){
        try {
            JwtParser parser = Jwts.parser().verifyWith(this.key).build();
            return parser.parseSignedClaims(token).getPayload();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
