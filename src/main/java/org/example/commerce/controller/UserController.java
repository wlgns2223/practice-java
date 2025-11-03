package org.example.commerce.controller;

import lombok.RequiredArgsConstructor;
import org.example.commerce.dto.UserRequestDto;
import org.example.commerce.dto.UserResponseDto;
import org.example.commerce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto body){

        UserResponseDto userResponseDto = userService.createUser(body);

        return ResponseEntity
                .created(URI.create("/user" + "/" + userResponseDto.getId()))
                .body(userResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable("id") Long userId){
        UserResponseDto userResponseDto = userService.findUserById(userId);
        return ResponseEntity.ok(userResponseDto);

    }
}
