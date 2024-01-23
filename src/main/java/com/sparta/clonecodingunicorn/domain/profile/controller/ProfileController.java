package com.sparta.clonecodingunicorn.domain.profile.controller;

import com.sparta.clonecodingunicorn.domain.member.dto.MemberResponseDto;
import com.sparta.clonecodingunicorn.domain.profile.dto.UpdateRequestDto;
import com.sparta.clonecodingunicorn.domain.profile.service.ProfileService;
import com.sparta.clonecodingunicorn.global.security.MemberDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/mypage")
    public ResponseEntity<MemberResponseDto> getMyPage(@AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        return profileService.getMyPage(memberDetails);
    }

    @GetMapping("/profile")
    public ResponseEntity<MemberResponseDto> getProfile(@AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        return profileService.getProfile(memberDetails);
    }

    @PutMapping("/profile")
    public ResponseEntity<String> updateMember(@RequestBody UpdateRequestDto requestDto, @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        return profileService.updateMember(requestDto, memberDetails);
    }
}
