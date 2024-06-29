package com.fr3nm0.polls_backend.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;

//    @Email
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "Invalid email address")
    private String email;

    @NotBlank
    @Size(min = 8, max = 40, message = "Password must be between 8 and 40 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*_\\-])(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9!@#$%^&*_\\-]{8,50}$", message = "Password must contain at least 1 uppercase letter, 1 lowercase letter, 1 number and 1 special character")
    private String password;
}
