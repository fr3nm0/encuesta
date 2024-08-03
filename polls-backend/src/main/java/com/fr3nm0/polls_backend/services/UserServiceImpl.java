package com.fr3nm0.polls_backend.services;

import com.fr3nm0.polls_backend.entities.UserEntity;
import com.fr3nm0.polls_backend.models.requests.LoginRequest;
import com.fr3nm0.polls_backend.models.requests.RegisterRequest;
import com.fr3nm0.polls_backend.models.responses.UserResponse;
import com.fr3nm0.polls_backend.repositories.UserRepository;
import com.fr3nm0.polls_backend.security.jwt.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;


    @Override
    public String login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.generateToken(authentication);
    }

    @Override
    public UserResponse createUser(RegisterRequest registerRequest) {
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(registerRequest, user);
        user.setEncryptedPassword(new BCryptPasswordEncoder().encode(registerRequest.getPassword()));
        userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
        return userResponse;
    }
}
