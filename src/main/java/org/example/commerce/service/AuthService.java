package org.example.commerce.service;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.commerce.authentification.JwtService;
import org.example.commerce.dto.*;
import org.example.commerce.entity.User;
import org.example.commerce.exception.PasswordNotMatch;
import org.example.commerce.exception.DuplicateException;
import org.example.commerce.exception.NotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

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

        if (!compare(signInDto.getPassword(), user.getPassword())) {
            throw new PasswordNotMatch("password not match");
        }

        return jwtService.createAccessToken(user.getEmail());
    }

    private Boolean compare(String raw,String encoded){
        return passwordEncoder.matches(raw, encoded);
    }

    public User parseToken(final TokenParseDto tokenParseDto){
        Claims claims = jwtService.parseToken(tokenParseDto.getToken());

        return userService.findUserByEmail(claims.getSubject())
                .orElseThrow(() -> new NotFoundException("Not Found User by Subject: " + claims.getSubject()));
    }
}
