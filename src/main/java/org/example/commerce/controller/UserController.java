package org.example.commerce.controller;

import lombok.RequiredArgsConstructor;
import org.example.commerce.dto.UserRequestDto;
import org.example.commerce.dto.UserResponseDto;
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

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> patch(@PathVariable("id") Long userId, @RequestBody UserRequestDto dto){
        UserResponseDto userResponseDto = userService.updateUserById(userId, dto);
        return ResponseEntity.ok(userResponseDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long userId){
        userService.deleteById(userId);
        return ResponseEntity.ok("success");
    }

}
