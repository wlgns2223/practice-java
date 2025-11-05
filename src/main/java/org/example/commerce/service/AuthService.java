package org.example.commerce.service;

import lombok.RequiredArgsConstructor;
import org.example.commerce.dto.UserRequestDto;
import org.example.commerce.dto.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    public UserResponseDto signUp(final UserRequestDto userRequestDto){
        userService.checkIfEmailExists(userRequestDto.getEmail());


    }


}
