package org.example.commerce.service;

import lombok.RequiredArgsConstructor;
import org.example.commerce.dto.SignInDto;
import org.example.commerce.dto.UserMapper;
import org.example.commerce.dto.UserRequestDto;
import org.example.commerce.dto.UserResponseDto;
import org.example.commerce.entity.User;
import org.example.commerce.exception.AuthException;
import org.example.commerce.exception.DuplicateException;
import org.example.commerce.exception.NotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public User signUp(final UserRequestDto userRequestDto){
        if(userService.findUserByEmail(userRequestDto.getEmail()).isPresent()){
            throw new DuplicateException("Duplicate email: " + userRequestDto.getEmail());
        }
        userRequestDto.setPassword(encodePassword(userRequestDto.getPassword()));

        return userService.createUser(userRequestDto);
    }

    private String encodePassword(String original){
        return passwordEncoder.encode(original);
    }

    public String signIn(final SignInDto signInDto){
        User user = userService
                .findUserByEmail(signInDto.getEmail())
                .orElseThrow(() -> new NotFoundException("Not Found User by Email: " + signInDto.getEmail()));

        if (compare(signInDto.getPassword(), user.getPassword())) {
            throw new AuthException("password not match");
        }

        return "some token...";
    }

    private Boolean compare(String raw,String encoded){
        return passwordEncoder.matches(raw, encoded);
    }


}
