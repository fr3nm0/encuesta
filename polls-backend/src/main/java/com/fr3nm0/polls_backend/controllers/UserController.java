package com.fr3nm0.polls_backend.controllers;

import com.fr3nm0.polls_backend.models.requests.LoginRequest;
import com.fr3nm0.polls_backend.models.requests.RegisterRequest;
import com.fr3nm0.polls_backend.models.responses.UserResponse;
import com.fr3nm0.polls_backend.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

}
