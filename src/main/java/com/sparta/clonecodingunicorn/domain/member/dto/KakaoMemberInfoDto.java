package com.sparta.clonecodingunicorn.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoMemberInfoDto {
    private Long id;
    private String nickname;
    private String email;

    public KakaoMemberInfoDto(Long id, String nickname, String email) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }
}