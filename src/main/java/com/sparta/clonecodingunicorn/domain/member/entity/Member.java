package com.sparta.clonecodingunicorn.domain.member.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private Integer birthYear;

    @Column
    private String gender;

    @Column
    private String emoji;

    @Column
    private String job;

    @Column
    private String interestArea;

    @Column
    private Boolean deleteMember;

    @Column
    private Boolean subscribeAgree;

    private Long kakaoId;

    @Builder
    public Member(Long memberId, String email, String password, String name, Integer birthYear, String gender, String emoji, String job, String interestArea, Boolean deleteMember, Boolean subscribeAgree) {
        this.memberId = memberId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthYear = birthYear;
        this.gender = gender;
        this.emoji = emoji;
        this.job = job;
        this.interestArea = interestArea;
        this.deleteMember = deleteMember;
        this.subscribeAgree = subscribeAgree;
    }

    public Member(String email, String encryptedPassword, String name) {
        this.email = email;
        this.password = encryptedPassword;
        this.name = name;
    }

    public Member(String email, String encryptedPassword, String name, Long kakaoId) {
        this.email = email;
        this.password = encryptedPassword;
        this.name = name;
        this.kakaoId = kakaoId;
    }

    public Member kakaoIdUpdate(Long kakaoId) {
        this.kakaoId = kakaoId;
        return this;
    }
}