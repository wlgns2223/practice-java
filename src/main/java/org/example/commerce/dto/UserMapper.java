package org.example.commerce.dto;

import org.example.commerce.entity.User;

public class UserMapper {

    public static UserResponseDto toResponseDto(User user){
        return UserResponseDto.builder().id(user.getId()).username(user.getUsername()).build();
    }
}
