package com.ThoughtNest.UserService.Utility;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
@Component
public class JwtUtil {
    @Value("${jwt.secret-key}")
    private String secretKey;
    private SecretKey getSigninKey(){
        System.out.println(secretKey);
        byte[] keyByte = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyByte);
    }
    public Claims getClaims(String token){
        return  Jwts
                .parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public boolean validateToken(String token){
        try{
            getClaims(token);
            return  true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return  false;
        }
    }
}
