package org.example.commerce.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.commerce.dto.SignInDto;
import org.example.commerce.dto.TokenParseDto;
import org.example.commerce.dto.UserMapper;
import org.example.commerce.dto.UserRequestDto;
import org.example.commerce.entity.User;
import org.example.commerce.service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody UserRequestDto userRequestDto){
        log.debug(userRequestDto.toString());
        return ResponseEntity.ok(UserMapper.toResponseDto(authService.signUp(userRequestDto)));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Object> signIn(@RequestBody SignInDto signInDto){
        String jwt = authService.signIn(signInDto);
        ResponseCookie cookie = ResponseCookie.from("access_token", jwt)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(Duration.ofMinutes(60))
                .sameSite("Lax")
                .build();

        return ResponseEntity.ok().
                header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(Map.of("message", "success"));
    }

    @PostMapping("parse")
    public ResponseEntity<User> parse(@RequestBody TokenParseDto tokenParseDto){
        return ResponseEntity.ok(authService.parseToken(tokenParseDto));
    }
}
