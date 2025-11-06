package org.example.commerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInDto {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Min(value = 4)
    private String password;
}
