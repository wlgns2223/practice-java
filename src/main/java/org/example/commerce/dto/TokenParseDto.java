package org.example.commerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TokenParseDto {
    @NotBlank
    private String token;
}
