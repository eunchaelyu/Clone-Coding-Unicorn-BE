package com.sparta.clonecodingunicorn.domain.member.dto;

import com.sparta.clonecodingunicorn.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateRequestDto {
    private String name;
    private Integer birthYear;
    private String gender;
    private String job;
    private String interestArea;
    private Boolean deleteMember;
    private Boolean subscribeAgree;

    @Builder
    public Member updateMemberEntity() {
        return Member.builder()
                .name(this.name)
                .birthYear(this.birthYear)
                .gender(this.gender)
                .job(this.job)
                .interestArea(this.interestArea)
                .deleteMember(this.deleteMember)
                .subscribeAgree(this.subscribeAgree)
                .build();
    }
}
