package ru.nsu.common.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String BEARER_PREFIX = "Bearer ";

    @Value("${application.token.signing.key}")
    private String jwtSigningKey;

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String userName = extractUsername(token);
        Date expiration = extractExpiration(token);

        boolean isMatchingUsername = userName.equals(userDetails.getUsername());
        boolean isStillNotExpired = expiration.after(new Date());

        return isMatchingUsername && isStillNotExpired;
    }

    public String generateToken(UserDetails userDetails, Long timeToLive) {
        return Jwts.builder()
            .subject(userDetails.getUsername())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + timeToLive))
            .signWith(getSigningKey(), Jwts.SIG.HS256).compact();
    }

    public String extractUsername(String token) {
        if (token.startsWith(BEARER_PREFIX)) {
            return extractClaim(token.substring(BEARER_PREFIX.length()), Claims::getSubject);
        }

        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        Claims claims = Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();

        return claimsResolvers.apply(claims);
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);

        return Keys.hmacShaKeyFor(keyBytes);
    }

}
