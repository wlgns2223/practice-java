package org.example.commerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.commerce.constant.Role;
import org.example.commerce.dto.UserMapper;
import org.example.commerce.dto.UserRequestDto;
import org.example.commerce.dto.UserResponseDto;
import org.example.commerce.dto.UserUpdateDto;
import org.example.commerce.entity.User;
import org.example.commerce.exception.DuplicateException;
import org.example.commerce.exception.NotFoundException;
import org.example.commerce.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findUserByEmail(final String email){
        return userRepository.findUserByEmail(email);
    }

    public User createUser(UserRequestDto userRequestDto){
        User user = User.builder()
                .username(userRequestDto.getUsername())
                .password(userRequestDto.getPassword())
                .username(userRequestDto.getUsername())
                .email(userRequestDto.getEmail())
                .role(Role.USER)
                .build();

        User createdUser = userRepository.save(user);
        log.info(createdUser.toString());

        return createdUser;

    }

    public User findUserById(Long id){
        return userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("User Not Found By ID" + id));

    }

    @Transactional
    public User updateUserById(Long id , UserUpdateDto userUpdateDto){
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User Not Found By Id: " + id));

        if(userUpdateDto.getUsername() != null){
            user.updateUsername(userUpdateDto.getUsername());
        }
        if(userUpdateDto.getPassword() != null){
            user.updatePassword(userUpdateDto.getPassword());
        }
        if(userUpdateDto.getEmail() != null){
            user.updateEmail(userUpdateDto.getEmail());
        }
        if(userUpdateDto.getRole() != null){
            user.updateRole(userUpdateDto.getRole());
        }

        return userRepository.save(user);
    }

    public void deleteById(Long userId){
        userRepository.deleteById(userId);
    }
}
