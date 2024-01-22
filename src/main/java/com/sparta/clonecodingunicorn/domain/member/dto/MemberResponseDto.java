package com.sparta.clonecodingunicorn.domain.member.dto;

import com.sparta.clonecodingunicorn.domain.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {
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


    public MemberResponseDto(Member member) {
        this.memberId = member.getMemberId();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.name = member.getName();
        this.birthYear = member.getBirthYear();
        this.gender = member.getGender();
        this.job = member.getJob();
        this.interestArea = member.getInterestArea();
        this.deleteMember = member.getDeleteMember();
        this.subscribeAgree = member.getSubscribeAgree();
    }
}