package org.example.commerce.dto;

import lombok.Data;
import org.example.commerce.constant.Role;

@Data
public class UserUpdateDto {

    private String username;

    private String password;

    private String email;

    private Role role;
}
