package com.curso.base_spring_security.service;

import com.curso.base_spring_security.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${security.jwt.expiration-minutes}")
    private Long EXPIRATION_MINUTES;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    public String generateToken(User user, Map<String, Object> extraClaims) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date( issuedAt.getTime() + (EXPIRATION_MINUTES * 60 * 1000));

        return Jwts.builder()
                .claims(extraClaims)
                .subject(user.getUsername())
                .issuedAt(issuedAt)
                .expiration(expiration)
                //.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .header().add(Header.TYPE, Header.JWT_TYPE).and()
                .signWith(generateKey())
                .compact();
    }

    private SecretKey generateKey() {
        try {
            byte [] secretAsBytes = Decoders.BASE64.decode(SECRET_KEY);
            System.out.println("mi clave es: " + new String(secretAsBytes));
            return Keys.hmacShaKeyFor(secretAsBytes);
        }catch (Exception ex){
            return null;
        }
    }

    public String extractUserName(String jwt) {
        return getAllClaims(jwt).getSubject();
    }

    private Claims getAllClaims(String jwt) {
        return Jwts.parser().verifyWith(generateKey()).build()
                .parseSignedClaims(jwt).getPayload();
    }
}
