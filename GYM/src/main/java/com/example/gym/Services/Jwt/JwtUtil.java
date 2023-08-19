package com.example.gym.Services.Jwt;

import com.example.gym.Entities.Users;
import com.example.gym.Exceptions.NotFoundUserException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private String jwtSecret = "931949128c37ce0092f6eb8fc450f6e844f19103c6c626683437e76cef3bd855";
    private int jwtExpiratioData = 604800000;
    public String generateToken(Users authentication){
        String username = authentication.getUsername();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime()+jwtExpiratioData);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();
        return token;

    }
    private Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    public String getUserName(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();
        return username;
    }
    public boolean validateToken(String token) {
        try{
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        }catch (MalformedJwtException exception){
            throw new NotFoundUserException("Invalid Jw",HttpStatus.BAD_REQUEST);
        }catch (ExpiredJwtException exception){
            throw new NotFoundUserException("Expired JWT token." , HttpStatus.BAD_REQUEST);
        }catch (UnsupportedJwtException exception){
            throw new NotFoundUserException("Unsupported JWT token." , HttpStatus.BAD_REQUEST);
        }catch (IllegalArgumentException exception){
            throw new NotFoundUserException("JWT claims string is empty." , HttpStatus.BAD_REQUEST);
        }

    }

}