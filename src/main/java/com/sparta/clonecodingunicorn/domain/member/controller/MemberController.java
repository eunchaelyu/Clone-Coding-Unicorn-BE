package com.sparta.clonecodingunicorn.domain.member.controller;

import com.sparta.clonecodingunicorn.domain.member.dto.UpdateRequestDto;
import com.sparta.clonecodingunicorn.domain.member.dto.SignupRequestDto;
import com.sparta.clonecodingunicorn.domain.member.dto.MemberResponseDto;
import com.sparta.clonecodingunicorn.domain.member.service.ProfileService;
import com.sparta.clonecodingunicorn.global.security.MemberDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MemberController {
    private final ProfileService profileService;

    public MemberController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid SignupRequestDto requestDto, BindingResult bindingResult) {
        return profileService.signup(requestDto, bindingResult);
    }

    @GetMapping("/profile")
    @ResponseBody
    public ResponseEntity<MemberResponseDto> findMember(@AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        MemberResponseDto responseDto = profileService.findMember(memberDetails);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/profile")
    public ResponseEntity<MemberResponseDto> updateMember(@RequestBody UpdateRequestDto requestDto, @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        MemberResponseDto responseDto = profileService.updateMember(requestDto, memberDetails);
        return ResponseEntity.ok(responseDto);
    }
}