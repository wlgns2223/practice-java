package org.example.commerce.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserRequestDto {
    private String username;
    private String password;
}
