package com.hjh.practice.member.dto;

import com.hjh.practice.member.constant.Department;
import com.hjh.practice.member.constant.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    
    @NotEmpty(message = "사용자 ID는 필수입니다.")
    @Size(min = 3, max = 30, message = "사용자 ID는 3~30자로 입력하세요.")
    private String username;

    @NotEmpty(message = "비밀번호는 필수입니다.")
    @Size(min = 4, message = "비밀번호는 4자 이상으로 입력하세요.")
    private String password1;

    @NotEmpty(message = "비밀번호 확인은 필수입니다.")
    private String password2;

    @NotEmpty(message = "이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotEmpty(message = "성별을 선택하세요.")
    private Gender gender;

    @NotEmpty(message = "학과를 선택하세요.")
    private Department department;

    @AssertTrue(message = "이용약관에 동의해야 가입할 수 있습니다.")
    private Boolean registration;
}
