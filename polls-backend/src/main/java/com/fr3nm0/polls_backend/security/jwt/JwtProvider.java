package com.fr3nm0.polls_backend.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtProvider {

    private String stringSecret = "Y8VaQNRF6Mh2e9pDcUzSEyKZXTCx7JnBkPwftbHvIEUsOXoKgIlTEJoJLNemiIDMnIzlOFLtCgj5mJNJiewOIyDADPgryrpiNFgOkidyPXweeJF";
    private long expirationDate = 864000000;

    //Generate JWT token
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + expirationDate);

        return  Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(key())
                .compact();

    }

    private Key key(){
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(stringSecret));
    }

    //Get username from JWT token
    public String getUsername(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public Boolean validateToken(String token){
        Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parse(token);

        return true;
    }

}
