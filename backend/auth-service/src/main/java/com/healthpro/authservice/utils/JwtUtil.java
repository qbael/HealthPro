package com.healthpro.authservice.utils;

import com.healthpro.authservice.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    private final Key secretKey;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        byte[] keyBytes = Base64.getDecoder().decode(secret.getBytes(
                StandardCharsets.UTF_8));
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public UUID extractId(String token) {
        Object idClaim = parseClaims(token).get("id");
        if (idClaim == null) throw new IllegalArgumentException("JWT does not contain 'id' claim");
        return UUID.fromString(idClaim.toString());
    }

    public String extractEmail(String token) {
        return parseClaims(token).getSubject();
    }

    public Map<String, Object> extractRoleClaim(String token) {
        Object roleClaim = parseClaims(token).get("role");
        if (!(roleClaim instanceof Map)) throw new IllegalArgumentException("JWT role claim is invalid");
        return (Map<String, Object>) roleClaim;
    }

    public Role extractRole(String token) {
        Map<String, Object> roleMap = extractRoleClaim(token);
        return new Role(
                UUID.fromString(roleMap.get("id").toString()),
                roleMap.get("roleName").toString()
        );
    }

    public String generateToken(String email, UUID id, Role role) {
        Map<String, Object> roleClaim = Map.of(
                "id", role.getId().toString(),
                "roleName", role.getRoleName()
        );

        return Jwts.builder()
                .subject(email)
                .claim("id", id)
                .claim("role", roleClaim)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24))) // 24 hours
                .signWith(secretKey)
                .compact();
    }

//    public void validateToken(String token) {
//        parseClaims(token);
//    }

    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith((SecretKey) secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException e) {
            throw new JwtException("Invalid JWT: " + e.getMessage(), e);
        }
    }
}
