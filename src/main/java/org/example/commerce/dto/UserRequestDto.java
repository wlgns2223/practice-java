package org.example.commerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDto {
    @NotBlank(message = "유저네임을 반드시 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호는 공백을 포함할 수 없습니다.")
    @Min(value = 4, message = "비밀번호는 최소 4자리입니다.")
    private String password;

    @Email
    private String email;
}
