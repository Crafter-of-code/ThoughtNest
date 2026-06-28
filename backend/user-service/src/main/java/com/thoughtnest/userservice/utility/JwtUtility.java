package com.thoughtnest.userservice.utility;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtUtility {
    private static final String secret_key = "OGVLcFgybVI5dkw0cVQ3blljVzVzRGZIMXpCak42dUFhRzNyUGtFOHhMbVEwd1Z6";
    public String generateToken(String email){
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60 * 24
                        )
                )
                .signWith(
                        Keys.hmacShaKeyFor(
                                secret_key.getBytes()
                        ),
                        Jwts.SIG.HS256
                )
                .compact();
    }
    public String extractEmail(String token){
        return Jwts.parser()
                .verifyWith(
                        Keys.hmacShaKeyFor(
                                secret_key.getBytes()
                        )
                )
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
    public boolean isTokenValid(String token){
        try {

            Jwts.parser()
                    .verifyWith(
                            Keys.hmacShaKeyFor(
                                    secret_key.getBytes()
                            )
                    )
                    .build()
                    .parseSignedClaims(token);

            return true;

        } catch (Exception e) {

            return false;
        }
    }

}
