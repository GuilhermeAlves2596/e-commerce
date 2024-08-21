package com.alves.login.service.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenProvider {

    private final String secret;

    @Autowired
    public JwtTokenProvider(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }


    public String generateToken(String login, Long id){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000);

        Map<String, Object> claims = new HashMap<>();
        claims.put("login", login);
        claims.put("id", id);
        claims.put("created", now);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
