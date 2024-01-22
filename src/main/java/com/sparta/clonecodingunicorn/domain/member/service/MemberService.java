package com.sparta.clonecodingunicorn.domain.member.service;

import com.sparta.clonecodingunicorn.domain.member.dto.SignupRequestDto;
import com.sparta.clonecodingunicorn.domain.member.dto.SignupResponseDto;
import com.sparta.clonecodingunicorn.domain.member.entity.Member;
import com.sparta.clonecodingunicorn.domain.member.repository.MemberRepository;
import com.sparta.clonecodingunicorn.global.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Slf4j
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // 1. 가입
    public ResponseEntity<String> signup(SignupRequestDto requestDto, BindingResult bindingResult) {
        log.info("[log] signup: 가입 시도");

        // 유효성 검사
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                throw new IllegalArgumentException("[IllegalArgumentException] " + fieldError.getDefaultMessage());
            }
        }

        // E-mal 중복 검사
        memberRepository.findByEmail(requestDto.getEmail())
                .ifPresent(user -> {
                    throw new IllegalArgumentException("[IllegalArgumentException 발생] 이미 가입된 사용자 정보가 존재합니다.");
                });

        // 패스워드 암호화 및 등록
        String encryptedPassword = passwordEncoder.encode(requestDto.getPassword());
        Member member = new Member(requestDto.getEmail(), encryptedPassword, requestDto.getName());
//        Member member = requestDto.createMember();
        memberRepository.save(member);
        log.info("[log] signup: 가입 완료");


        return ResponseEntity.status(HttpStatus.CREATED).body("가입이 완료되었습니다.");
    }
}