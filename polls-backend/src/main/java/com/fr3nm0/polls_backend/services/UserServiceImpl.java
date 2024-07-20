package com.fr3nm0.polls_backend.services;

import com.fr3nm0.polls_backend.entities.UserEntity;
import com.fr3nm0.polls_backend.models.requests.RegisterRequest;
import com.fr3nm0.polls_backend.models.responses.UserResponse;
import com.fr3nm0.polls_backend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(RegisterRequest request) {

        UserEntity userEntity = new UserEntity();

        // transfers data of the same name of RegisterRequest to UserEntity
        BeanUtils.copyProperties(request, userEntity);

        // setting encrypt password
        userEntity.setEncryptedPassword(new BCryptPasswordEncoder().encode(request.getPassword()));

        UserEntity user = userRepository.save(userEntity);

        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
        return userResponse;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new User(user.getEmail(), user.getEncryptedPassword(), new ArrayList<>());
    }
}
