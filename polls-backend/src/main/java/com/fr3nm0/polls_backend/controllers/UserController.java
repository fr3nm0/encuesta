package com.fr3nm0.polls_backend.controllers;

import com.fr3nm0.polls_backend.models.requests.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public String createUser(@RequestBody @Valid RegisterRequest request) {
        return "Create user";
    }
}
