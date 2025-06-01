package com.e_commerce_microservice_app.payment_service.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.function.Function;


@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String findUserName(String token){
        return exportToken(token, Claims::getSubject);
    }

    private <T> T exportToken(String token, Function<Claims, T> claimsTFunction){
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claimsTFunction.apply(claims);
    }

    public boolean tokenControl(String jwt){
        try {
            exportToken(jwt, Claims::getExpiration);
            return true;
        }catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException e){
            return false;
        }
    }
}

