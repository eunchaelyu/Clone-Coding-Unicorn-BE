package com.sparta.clonecodingunicorn.domain.member.controller;

import com.sparta.clonecodingunicorn.domain.member.dto.SignupRequestDto;
import com.sparta.clonecodingunicorn.domain.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid SignupRequestDto requestDto, BindingResult bindingResult) {
        return memberService.signup(requestDto, bindingResult);
    }

}