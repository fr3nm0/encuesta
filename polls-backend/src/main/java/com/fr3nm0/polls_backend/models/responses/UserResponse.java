package com.fr3nm0.polls_backend.models.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private long id;
    private String name;
    private String email;
}
