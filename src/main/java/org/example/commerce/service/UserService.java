package org.example.commerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.commerce.constant.Role;
import org.example.commerce.dto.UserMapper;
import org.example.commerce.dto.UserRequestDto;
import org.example.commerce.dto.UserResponseDto;
import org.example.commerce.entity.User;
import org.example.commerce.exception.DuplicateException;
import org.example.commerce.exception.NotFoundException;
import org.example.commerce.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private boolean findUserByEmail(final String email){
        return userRepository.findUserByEmail(email);
    }

    public void checkIfEmailExists(final String email){
        boolean emailExists = findUserByEmail(email);
        if(emailExists){
            throw new DuplicateException("이메일 " + email + "이 이미 존재합니다");
        }
    }

    public UserResponseDto createUser(UserRequestDto userRequestDto){
        User user = User.builder()
                .username(userRequestDto.getUsername())
                .password(userRequestDto.getPassword())
                .username(userRequestDto.getUsername())
                .email(userRequestDto.getEmail())
                .role(Role.USER)
                .build();

        User createdUser = userRepository.save(user);
        log.info(createdUser.toString());

        return UserMapper.toResponseDto(createdUser);

    }

    public UserResponseDto findUserById(Long id){
        return userRepository
                .findById(id)
                .map(UserMapper::toResponseDto)
                .orElseThrow(() -> new NotFoundException("User Not Found By ID" + id));

    }

    public UserResponseDto updateUserById(Long id , UserRequestDto userRequestDto){
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User Not Found By Id: " + id));

        if(userRequestDto.getUsername() != null){
            user.setUsername(userRequestDto.getUsername());
        }
        if(userRequestDto.getPassword() != null){
            user.setPassword(userRequestDto.getPassword());
        }
        if(userRequestDto.getEmail() != null){
            user.setEmail(userRequestDto.getEmail());
        }

        User created = userRepository.save(user);
        return UserMapper.toResponseDto(created);
    }

    public void deleteById(Long userId){
        userRepository.deleteById(userId);
    }
}
