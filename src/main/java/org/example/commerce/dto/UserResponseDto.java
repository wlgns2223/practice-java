package org.example.commerce.dto;

import lombok.*;
import org.example.commerce.constant.Role;

@Builder
@Data
public class UserResponseDto {

    private Long id;
    private String username;
    private String email;
    private Role role;
}
