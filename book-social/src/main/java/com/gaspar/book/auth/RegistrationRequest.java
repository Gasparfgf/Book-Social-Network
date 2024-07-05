package com.gaspar.book.auth;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class RegistrationRequest {

    //Because i use the @Valid annotation in the controller, these fields must be provided
    @NotEmpty(message = "Firstname is mandatory")
    @NotBlank(message =  "Firstname is mandatory")
    private String firstname;
    @NotEmpty(message = "Firstname is mandatory")
    @NotBlank(message =  "Firstname is mandatory")
    private String lastname;
    @NotEmpty(message = "Firstname is mandatory")
    @NotBlank(message =  "Firstname is mandatory")
    @Email(message = "Email is not formated. Exemple: gasparfgf@gmail.com")
    private String email;
    @NotEmpty(message = "Firstname is mandatory")
    @NotBlank(message =  "Firstname is mandatory")
    @Size(min = 8, message = "Password should be 8 characters long minimum")
    private String password;
}
