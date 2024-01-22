package com.sparta.clonecodingunicorn.domain.member.dto;

import com.sparta.clonecodingunicorn.domain.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequestDto {
    @NotBlank(message = "작성되지 않은 항목이 있습니다.")
    @Pattern(
            /**
             * 1. local part [a-zA-Z0-9._%+-]: 대소문자, 숫자, 마침표, 언더스코어, 퍼센트, 플러스, 하이픈 중 하나 이상의 문자 허용
             * 2-1. domain [a-zA-Z0-9.-]: 대소문자, 숫자, 마침표, 하이픈 중 하나 이상의 문자 허용
             * 2-2. domain [a-zA-Z]{2,}: 최소 두 개의 대소문자 필요
            */
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "유효한 이메일 형식이어야 합니다."
    )
    private String email;

    @NotBlank(message = "작성되지 않은 항목이 있습니다.")
    @Pattern(
            /**
             * 1.(?=.*[a-zA-Z]): 적어도 하나의 대소문자 필요
             * 2. (?=.*\\d):적어도 하나의 숫자 필요
             * 3. (?=.*[@$!%*?&]): 적어도 하나의 특수문자 필요
             * 4. [A-Za-z\\d@$!%*?&]{8,15}: 최소 8자 이상, 최대 15자 이하의 길이
            */
            regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$",
            message = "비밀번호는 8자에서 15자 사이의 알파벳 대소문자, 숫자, 특수문자로 구성되어야 합니다."
    )
    private String password;


    @NotBlank(message = "작성되지 않은 항목이 있습니다.")
    private String name;

    public Member createMember() {
        return Member.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .build();
    }
}
