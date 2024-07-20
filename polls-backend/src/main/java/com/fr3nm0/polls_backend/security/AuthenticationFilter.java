package com.fr3nm0.polls_backend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr3nm0.polls_backend.models.requests.LoginRequest;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(
                HttpServletRequest request,
                HttpServletResponse response
    ) throws AuthenticationException {

        try{
            LoginRequest loginRequest = new ObjectMapper().readValue(request.getReader(),LoginRequest.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword(),
                            new ArrayList<>())
            );
        }catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authentication
    ) throws IOException, ServletException {

        String email = ((User) authentication.getPrincipal()).getUsername();
        SecretKey key = new SecretKeySpec("s3cr3t_key_t0k3n".getBytes(), Jwts.SIG.HS512.key().toString());
        //Generate token
        String token = Jwts.builder()
                .subject(email)
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(key)
                .compact();

        System.out.println(token);
    }
}