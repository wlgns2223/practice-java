package org.example.commerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.commerce.dto.UserMapper;
import org.example.commerce.dto.UserRequestDto;
import org.example.commerce.dto.UserResponseDto;
import org.example.commerce.entity.User;
import org.example.commerce.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto createUser (UserRequestDto userRequestDto){
        User user = User.builder()
                .username(userRequestDto.getUsername())
                .password(userRequestDto.getPassword())
                .username(userRequestDto.getUsername())
                .build();

        User createdUser = userRepository.save(user);
        log.info(createdUser.toString());

        return UserMapper.toResponseDto(createdUser);

    }
}
