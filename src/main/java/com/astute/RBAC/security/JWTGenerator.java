package com.astute.RBAC.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JWTGenerator {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(Authentication authentication) {
        System.out.println("---------------------------------------------------------------");
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt( new Date())
                .setExpiration(expireDate)
                .signWith(key,SignatureAlgorithm.HS512)
                .compact();
        System.out.println("New token :");
        System.out.println(token);
        System.out.println("------------------------------------------------"+token);
        return token;
    }

    public String getUserNameFromJWT(String token){
        System.out.println("-----------------------------------------user name from jwt: " + token);
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    public boolean validateToken(String token) {
        System.out.println("-----------------------------------------Token to validate: " + token);

//        try {
//            Jwts.parser()
//                    .setSigningKey(key)
//                    .build()
//                    .parseClaimsJws(token);
//            return true;
//        } catch (Exception ex) {
//            throw new AuthenticationCredentialsNotFoundException("JWT was exprired or incorrect",ex.fillInStackTrace());
//        }
        try {
            Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            // Handle token expiration
            throw new RuntimeException("JWT token has expired", e);
        } catch (JwtException e) {
            // Handle other JWT exceptions
            throw new RuntimeException("Invalid JWT token", e);
        }
        return true;
    }
}
