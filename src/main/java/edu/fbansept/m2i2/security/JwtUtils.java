package edu.fbansept.m2i2.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtUtils {

    @Value("${jwt.secret}")
    String jwtSecret;

    public String generateToken(AppUserDetails userDetails) {

       return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

    }

    public String extractSubject(String jwt) {

        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }

}
