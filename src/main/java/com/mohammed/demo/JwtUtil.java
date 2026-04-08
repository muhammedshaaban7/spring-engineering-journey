package com.mohammed.demo;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // الـ Secret Key - لازم تكون 32 character على الأقل
    private final String SECRET = "mohammed-secret-key-12345678901234";
    private final long EXPIRATION = 1000 * 60 * 60; // ساعة واحدة

    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // بيعمل Token جديد - زي GenerateToken في .NET
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getKey())
                .compact();
    }

    // بيجيب الـ Username من الـ Token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // بيتحقق إن الـ Token صح
    public boolean isValid(String token) {
        try {
            extractUsername(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}