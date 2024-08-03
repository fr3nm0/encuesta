package com.fr3nm0.polls_backend.services;

import com.fr3nm0.polls_backend.models.requests.LoginRequest;
import com.fr3nm0.polls_backend.models.requests.RegisterRequest;
import com.fr3nm0.polls_backend.models.responses.UserResponse;

public interface UserService {
    String login(LoginRequest loginRequest);

    UserResponse createUser(RegisterRequest registerRequest);
}
