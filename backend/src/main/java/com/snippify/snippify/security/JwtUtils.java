package com.snippify.snippify.security;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

import java.nio.charset.StandardCharsets;

import io.jsonwebtoken.Jwts;

import java.util.Date;

import io.jsonwebtoken.Claims;




@Component

public class JwtUtils {

    @Value("${jwt.secret}")
    
    private String secretKey;

    @Value("${jwt.expiration}")

    private Long expiration; 

    private SecretKey getSigningKey() {
    return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)); 
    }

    //build the token
    public String generateToken(String email) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
            //who is this for
            .subject(email)
            //when was it created
            .issuedAt(now)
            //when does it expire
            .expiration(expireDate)
            //sign it
            .signWith(getSigningKey())
            //build it
            .compact(); 
    }

    //get email
    public String extractEmail(String token) {

        Claims claims = Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
            
        return claims.getSubject(); 
            

    }

    //validate the token
    public boolean validateToken(String token) {

        try {
            Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token);
            //token is valid if it works
            return true;
        } catch (Exception e) {
            return false;
        }

    }


}


