package com.sparta.clonecodingunicorn.domain.profile.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateRequestDto {
    private String name;
    private Integer birthYear;
    private String gender;
    private String emoji;
    private String job;
    private String interestArea;
    private Boolean deleteMember;
    private Boolean subscribeAgree;
}
