package org.example.commerce.controller;

import lombok.RequiredArgsConstructor;
import org.example.commerce.dto.UserMapper;
import org.example.commerce.dto.UserRequestDto;
import org.example.commerce.dto.UserResponseDto;
import org.example.commerce.dto.UserUpdateDto;
import org.example.commerce.repository.UserRepository;
import org.example.commerce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto body){

        UserResponseDto userResponseDto = UserMapper.toResponseDto(userService.createUser(body));
        return ResponseEntity
                .created(URI.create("/user" + "/" + userResponseDto.getId()))
                .body(userResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable("id") Long userId){
        return ResponseEntity.ok(UserMapper.toResponseDto(userService.findUserById(userId)));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> patch(@PathVariable("id") Long userId, @RequestBody UserUpdateDto dto){
        return ResponseEntity.ok(UserMapper.toResponseDto(userService.updateUserById(userId, dto)));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long userId){
        userService.deleteById(userId);
        return ResponseEntity.ok("success");
    }

}
