package org.example.commerce.controller;

import lombok.RequiredArgsConstructor;
import org.example.commerce.dto.SignInDto;
import org.example.commerce.dto.UserMapper;
import org.example.commerce.dto.UserRequestDto;
import org.example.commerce.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.ok(UserMapper.toResponseDto(authService.signUp(userRequestDto)));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Object> signIn(@RequestBody SignInDto signInDto){
        return ResponseEntity.ok(authService.signIn(signInDto));
    }
}
