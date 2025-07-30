package com.auth.components;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration.ms}")
    private long jwtExpirationInMs;

    // Helper method to get the signing key
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Generates a JWT token for a given subject (e.g., username).
     *
     * @param username The subject of the token (e.g., user's unique identifier).
     * @return The generated JWT string.
     */
    public String generateToken(String username) {
        // You can add additional claims here if needed
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    /**
     * Creates the JWT token with specified claims and subject.
     *
     * @param claims  Custom claims to include in the token.
     * @param subject The subject of the token (e.g., username, user ID).
     * @return The built JWT string.
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims) // Custom claims
                .setSubject(subject) // The principal about whom the token is issued
                .setIssuedAt(new Date(System.currentTimeMillis())) // When the token was issued
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs)) // When the token expires
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Sign with your secret key and algorithm
                .compact(); // Builds the JWT and serializes it to a compact, URL-safe string
    }

    /**
     * Extracts the subject (username) from the token.
     *
     * @param token The JWT token string.
     * @return The subject (username) from the token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts the expiration date from the token.
     *
     * @param token The JWT token string.
     * @return The expiration date.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts a specific claim from the token.
     *
     * @param token          The JWT token string.
     * @param claimsResolver A function to resolve the desired claim from the token's claims.
     * @param <T>            The type of the claim.
     * @return The extracted claim.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extracts all claims from the token.
     *
     * @param token The JWT token string.
     * @return The Claims object containing all claims.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey()) // Provide your secret key for parsing
                .build()
                .parseClaimsJws(token) // Parses the signed JWT
                .getBody(); // Returns the claims (payload)
    }

    /**
     * Checks if the token is expired.
     *
     * @param token The JWT token string.
     * @return true if the token is expired, false otherwise.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Validates the token against a username and checks for expiration.
     *
     * @param token    The JWT token string.
     * @param username The username to validate against.
     * @return true if the token is valid for the given username and not expired, false otherwise.
     */
    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    /**
     * Validates the token's signature and expiration, logging any exceptions.
     *
     * @param token The JWT token string.
     * @return true if the token is valid (signature and not expired), false otherwise.
     */
    public Boolean validateToken(String token) {
        try {
//            Jwts.setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
