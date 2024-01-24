package com.sparta.clonecodingunicorn.domain.profile.service;

import com.sparta.clonecodingunicorn.domain.member.dto.MemberResponseDto;
import com.sparta.clonecodingunicorn.domain.member.entity.Member;
import com.sparta.clonecodingunicorn.domain.member.repository.MemberRepository;
import com.sparta.clonecodingunicorn.domain.profile.dto.UpdateRequestDto;
import com.sparta.clonecodingunicorn.global.security.MemberDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ProfileService {

    private final MemberRepository memberRepository;

    public ProfileService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 마이페이지 조회
    public ResponseEntity<MemberResponseDto> getMyPage(MemberDetailsImpl memberDetails) {
        return getProfileOrMyPage(memberDetails);
    }

    // 프로필 조회
    public ResponseEntity<MemberResponseDto> getProfile(MemberDetailsImpl memberDetails) {
        return getProfileOrMyPage(memberDetails);
    }

    // 마이페이지와 프로필 비지니스 로직
    private ResponseEntity<MemberResponseDto> getProfileOrMyPage(MemberDetailsImpl memberDetails) {
        log.info("[log] getProfile: 프로필 조회 시도");

        String username = memberDetails.getUsername();
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        log.info("[log] getProfile: 프로필 조회 완료");

        MemberResponseDto responseDto = MemberResponseDto.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .name(member.getName())
                .birthYear(member.getBirthYear())
                .gender(member.getGender())
                .emoji(member.getEmoji())
                .job(member.getJob())
                .interestArea(member.getInterestArea())
                .deleteMember(member.getDeleteMember())
                .subscribeAgree(member.getSubscribeAgree())
                .build();

        return ResponseEntity.ok(responseDto);
    }

    // 프로필 업데이트
    @Transactional
    public ResponseEntity<String> updateMember(UpdateRequestDto requestDto, MemberDetailsImpl memberDetails) {
        log.info("[log] updateProfile: 프로필 수정 시도");

        String username = memberDetails.getUsername();
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        updateMemberProfile(member, requestDto);
        memberRepository.save(member);

        log.info("[log] updateProfile: 프로필 수정 완료");
        return ResponseEntity.ok("프로필이 수정되었습니다.");
    }

    // 개별 프로필 정보 수정 로직
    private void updateMemberProfile(Member member, UpdateRequestDto requestDto) {
        if (requestDto.getName() != null) {
            member.setName(requestDto.getName());
        }
        if (requestDto.getBirthYear() != null) {
            member.setBirthYear(requestDto.getBirthYear());
        }
        if (requestDto.getGender() != null) {
            member.setGender(requestDto.getGender());
        }
        if (requestDto.getEmoji() != null) {
            member.setEmoji(requestDto.getEmoji());
        }
        if (requestDto.getJob() != null) {
            member.setJob(requestDto.getJob());
        }
        if (requestDto.getInterestArea() != null) {
            member.setInterestArea(requestDto.getInterestArea());
        }
        if (requestDto.getSubscribeAgree() != null) {
            member.setSubscribeAgree(requestDto.getSubscribeAgree());
        }
    }

    // RequestParam 방식의 프로필 수정
    @Transactional
    public ResponseEntity<String> updateProfile(
            UpdateRequestDto requestDto,
            MemberDetailsImpl memberDetails
    ) {
        log.info("[log] updateProfile: 프로필 수정 시도");

        String username = memberDetails.getUsername();
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        updateMemberProfile(member, requestDto);
        memberRepository.save(member);

        log.info("[log] updateProfile: 프로필 수정 완료");
        return ResponseEntity.ok("프로필이 수정되었습니다.");
    }
}
