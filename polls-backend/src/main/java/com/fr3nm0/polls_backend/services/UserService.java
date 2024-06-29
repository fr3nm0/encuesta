package com.fr3nm0.polls_backend.services;

import com.fr3nm0.polls_backend.entities.UserEntity;
import com.fr3nm0.polls_backend.models.requests.RegisterRequest;
import com.fr3nm0.polls_backend.models.responses.UserResponse;

public interface UserService {

    public UserResponse createUser(RegisterRequest request);
}
