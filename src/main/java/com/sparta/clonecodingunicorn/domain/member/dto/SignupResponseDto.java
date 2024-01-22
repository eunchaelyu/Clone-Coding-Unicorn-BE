package com.sparta.clonecodingunicorn.domain.member.dto;

import lombok.Getter;

@Getter
public class SignupResponseDto {
    private Long memberId;
    private String email;
    private String password;
    private String name;
    private Integer birthYear;
    private String gender;
    private String job;
    private String interestArea;
    private Boolean deleteMember;
    private Boolean subscribeAgree;
}