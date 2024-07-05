package com.gaspar.book.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationRequest {
    @NotEmpty(message = "Email is mandatory")
    @NotBlank(message =  "Email is mandatory")
    @Email(message = "Email is not formated. Exemple: gasparfgf@gmail.com")
    private String email;
    @NotEmpty(message = "password is mandatory")
    @NotBlank(message =  "password is mandatory")
    @Size(min = 8, message = "Password should be 8 characters long minimum")
    private String password;
}
