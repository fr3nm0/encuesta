package com.fr3nm0.polls_backend.services;

import com.fr3nm0.polls_backend.entities.UserEntity;
import com.fr3nm0.polls_backend.models.requests.RegisterRequest;
import com.fr3nm0.polls_backend.models.responses.UserResponse;
import com.fr3nm0.polls_backend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public UserResponse createUser(RegisterRequest request) {
        UserEntity userEntity = new UserEntity();

        // transfers data of the same name of RegisterRequest to UserEntity
        BeanUtils.copyProperties(request, userEntity);

        // setting encrypt password
        userEntity.setEncryptedPassword(request.getPassword());

        UserEntity user = userRepository.save(userEntity);

        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
        return userResponse;
    }
}
