package com.umadecruz.app.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "CHAVE-SECRETA-SUPER-FORTE-UMADECRUZ-API-123456789012345";

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 2;           // 2 horas
    private static final long REFRESH_EXPIRATION_TIME = 1000L * 60 * 60 * 24 * 7; // 7 dias

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    // ---------------------------
    // TOKENS
    // ---------------------------
    public String generateToken(String username, Map<String, Object> extraClaims) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSignKey())
                .compact();
    }

    public String generateToken(String username) {
        return generateToken(username, Map.of());
    }

    // --- Versão que você usa (String, String)
    public String createToken(String username, String role) {
        return generateToken(username, Map.of("role", role));
    }

    // --- Outras versões opcionais
    public String createToken(String username) {
        return generateToken(username);
    }

    public String createToken(String username, Map<String, Object> claims) {
        return generateToken(username, claims);
    }

    // ---------------------------
    // REFRESH TOKEN
    // ---------------------------
    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME))
                .signWith(getSignKey())
                .compact();
    }

    // ---------------------------
    // EXTRAÇÃO
    // ---------------------------
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // ---------------------------
    // VALIDAÇÃO
    // ---------------------------
    public boolean isTokenValid(String token, String username) {
        try {
            return extractUsername(token).equals(username) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
