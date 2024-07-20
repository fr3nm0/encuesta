package com.fr3nm0.polls_backend.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginRequest {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*_\\-])(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9!@#$%^&*_\\-]{8,50}$", message = "Password must contain at least 1 uppercase letter, 1 lowercase letter, 1 number and 1 special character")
    private String password;
}
