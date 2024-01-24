package com.sparta.clonecodingunicorn.domain.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.clonecodingunicorn.domain.member.dto.KakaoMemberInfoDto;
import com.sparta.clonecodingunicorn.domain.member.entity.Member;
import com.sparta.clonecodingunicorn.domain.member.repository.MemberRepository;
import com.sparta.clonecodingunicorn.global.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Slf4j(topic = "[kakao 로그인]")
@Service
public class KakaoService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final RestTemplate restTemplate;
    private final JwtUtil jwtUtil;

    public KakaoService(PasswordEncoder passwordEncoder, MemberRepository memberRepository, RestTemplate restTemplate, JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.memberRepository = memberRepository;
        this.restTemplate = restTemplate;
        this.jwtUtil = jwtUtil;
    }

    public String kakaoLogin(String code) throws JsonProcessingException {
        // 1. 인증 코드로 kakao 서버에 토큰 전달
        String accessToken = getToken(code);

        // 2. kakao 서버로부터 받은 토큰으로 API 호출(사용자 정보 요청)
        KakaoMemberInfoDto kakaoMemberInfo = getKakaoMemberInfo(accessToken);

        // 3. 필요에 따라 회원가입 진행 -> JWT 토큰 반환
        Member kakaoMember = registerKakaoMemberIfNeeded(kakaoMemberInfo);
        String token = jwtUtil.createToken(kakaoMember.getName());
        return token;
    }

    // 1. 인증 코드로 kakao 서버에 토큰 전달
    private String getToken(String code) throws JsonProcessingException {
        // 1-1. 토큰 요청 URL 만들기
        URI uri = UriComponentsBuilder
                .fromUriString("https://kauth.kakao.com")
                .path("/oauth/token")
                .encode()
                .build()
                .toUri();

        // 1-2. HTTP header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // 1-3. HTTP body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "671600df764bdd2a2198446875a85121"); // REST API 키 삽입
        body.add("redirect_uri", "https://lyriczen.store/api/kakao/callback"); // Redirect URI 삽입
        body.add("code", code); // 인증 코드 삽입

        RequestEntity<MultiValueMap<String, String>> requestEntity = RequestEntity
                .post(uri)
                .headers(headers)
                .body(body);

        // 1-4. HTTP 요청 보내기
        ResponseEntity<String> response = restTemplate.exchange(
                requestEntity,
                String.class
        );

        // 1-5. HTTP 응답(JSON) -> access token 파싱
        JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
        return jsonNode.get("access_token").asText();
    }

    // 2. kakao 서버로부터 받은 토큰으로 API 호출(사용자 정보 요청)
    private KakaoMemberInfoDto getKakaoMemberInfo(String accessToken) throws JsonProcessingException {
        // 2-1. 사용자 정보 요청 URL 만들기
        URI uri = UriComponentsBuilder
                .fromUriString("https://kapi.kakao.com")
                .path("/v2/user/me")
                .encode()
                .build()
                .toUri();

        // 2-2. HTTP header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        RequestEntity<MultiValueMap<String, String>> requestEntity = RequestEntity
                .post(uri)
                .headers(headers)
                .body(new LinkedMultiValueMap<>());

        // 2-3. HTTP 요청 보내기
        ResponseEntity<String> response = restTemplate.exchange(
                requestEntity,
                String.class
        );

        // 2-4. HTTP 응답(JSON) -> 사용자 정보 파싱
        JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
        Long id = jsonNode.get("id").asLong();
        String nickname = jsonNode.get("properties").get("nickname").asText();
        String email = jsonNode.get("kakao_account").get("email").asText();
        return new KakaoMemberInfoDto(id, nickname, email);
    }

    // 3. 필요에 따라 회원가입 진행
    private Member registerKakaoMemberIfNeeded(KakaoMemberInfoDto kakaoMemberInfo) {
        Long kakaoId = kakaoMemberInfo.getId();
        Member kakaoMember = memberRepository.findByKakaoId(kakaoId).orElse(null);

        // 3-1. NEWNEEK에서 kakao 로그인을 하지 않은 경우 -> DB 내 kakaoId 없음
        if (kakaoMember == null) {
            String kakaoEmail = kakaoMemberInfo.getEmail();
            Member sameEmailMember = memberRepository.findByEmail(kakaoEmail).orElse(null);
            // kakao email과 동일한 email을 가진 멤버가 DB에 존재하는 경우
            if (sameEmailMember != null) {
                // NEWNEEK에서 가입한 정보로 kakao 사용자 정보 대체 + kakaoID 업데이트 진행
                kakaoMember = sameEmailMember;
                kakaoMember = kakaoMember.kakaoIdUpdate(kakaoId);
            } else {
                // 신규로 가입하는 경우
                String password = UUID.randomUUID().toString();
                String encodedPassword = passwordEncoder.encode(password);
                String email = kakaoMemberInfo.getEmail();
                String name = kakaoMemberInfo.getNickname();
                kakaoMember = new Member(email, encodedPassword, name, kakaoId);
            }
            memberRepository.save(kakaoMember);
        }
        // 3-2. NEWNEEK에서 kakao 로그인을 한 경우 -> DB 내 kakaoId 있음
        return kakaoMember;
    }
}