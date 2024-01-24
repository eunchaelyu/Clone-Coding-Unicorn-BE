package com.sparta.clonecodingunicorn.domain.post.controller;

import com.sparta.clonecodingunicorn.domain.post.dto.PostRequestDto;
import com.sparta.clonecodingunicorn.domain.post.dto.PostResponseDto;
import com.sparta.clonecodingunicorn.domain.post.entity.Category;
import com.sparta.clonecodingunicorn.domain.post.service.PostService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/posts")
//@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

//    @PostMapping // 포스트 작성
//    public ResponseEntity<PostResponseDto> createPost(@RequestBody @Valid PostRequestDto requestDto) {
//        PostResponseDto responseDto = postService.createPost(requestDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
//    }

//    @PostMapping("/query") // 포스트 작성
//    public ResponseEntity<List<PostResponseDto>> createPost() {
//        List<PostResponseDto> responseList = postService.createPost();
//        return ResponseEntity.status(HttpStatus.CREATED).body(responseList);
//    }

    @GetMapping // 포스트 전체 조회
    public ResponseEntity<List<PostResponseDto>> getPostList() {
        List<PostResponseDto> responseList = postService.getPostList();
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @PutMapping("/{postId}") // 포스트 수정
    public ResponseEntity<PostResponseDto> updatePost(@RequestBody @Valid PostRequestDto requestDto, @PathVariable Long postId) {
        PostResponseDto responseDto =postService.updatePost(requestDto, postId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/{postId}") //포스트 삭제
    public ResponseEntity<PostResponseDto> deletePost(@PathVariable Long postId){
        PostResponseDto responseDto =postService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
    @GetMapping("/categories") //카테고리별 포스트 조회
    public ResponseEntity<List<PostResponseDto>> getSortedPostList(@RequestParam(name = "category") Category category) {
        List<PostResponseDto> responseList = postService.getSortedPostList(category);
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @GetMapping("/scrapeNaverNews")
    public String scrapeNaverNews() throws IOException {
        postService.scrapeNaverNews("정치");
        postService.scrapeNaverNews("경제");
        postService.scrapeNaverNews("세계");
        postService.scrapeNaverNews("테크");
        postService.scrapeNaverNews("노동");
        postService.scrapeNaverNews("환경");
        postService.scrapeNaverNews("인권");
        postService.scrapeNaverNews("사회");
        postService.scrapeNaverNews("문화");
        postService.scrapeNaverNews("라이프");
        return "Scraping in progress. Check console for details.";
    }

}