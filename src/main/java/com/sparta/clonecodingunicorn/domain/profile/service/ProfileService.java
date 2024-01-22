package com.sparta.clonecodingunicorn.domain.profile.service;

import com.sparta.clonecodingunicorn.domain.member.dto.MemberResponseDto;
import com.sparta.clonecodingunicorn.domain.member.dto.UpdateRequestDto;
import com.sparta.clonecodingunicorn.domain.member.entity.Member;
import com.sparta.clonecodingunicorn.domain.member.repository.MemberRepository;
import com.sparta.clonecodingunicorn.global.security.MemberDetailsImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {

    private final MemberRepository memberRepository;

    public ProfileService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponseDto findMember(MemberDetailsImpl memberDetails) {
        Member member = memberRepository.findById(memberDetails.getUser().getMemberId())
                .orElseThrow(IllegalArgumentException::new);
        return new MemberResponseDto(member);
    }
    @Transactional
    public MemberResponseDto updateMember(UpdateRequestDto requestDto, MemberDetailsImpl memberDetails) {
        memberRepository.findById(memberDetails.getUser().getMemberId()) // 객체
                .orElseThrow(IllegalArgumentException::new);

        Member member = requestDto.updateMemberEntity();
        memberRepository.save(member);
        return new MemberResponseDto(member);
    }
}
