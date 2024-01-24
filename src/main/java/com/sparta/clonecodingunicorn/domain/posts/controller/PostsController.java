package com.sparta.clonecodingunicorn.domain.posts.controller;

import com.sparta.clonecodingunicorn.domain.posts.dto.PostsDetailsResponseDto;
import com.sparta.clonecodingunicorn.domain.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/api/news")
//@RequiredArgsConstructor
//@Slf4j
//public class NewsController {
//
//    private final NewsService newsService;
//
//    @GetMapping()
//    public ResponseEntity<Map<String, Object>> getNews(@RequestParam("page") int page,
//                                                       @RequestParam("size") int size,
//                                                       @RequestParam("sortBy") String sortBy,
//                                                       @RequestParam("isAsc") boolean isAsc
//    ){
//
//        Map<String, Object> newsResponseDtoList = newsService.getNews(
//                page - 1,
//                size,
//                sortBy,
//                isAsc
//        );
//
//        return ResponseEntity.ok(newsResponseDtoList);
//    }
//
//    @GetMapping("/category")
//    public ResponseEntity<Map<String, Object>> getNewsByCategory(
//            @RequestParam("category") String category,
//            @RequestParam("page") int page,
//            @RequestParam("size") int size,
//            @RequestParam("sortBy") String sortBy,
//            @RequestParam("isAsc") boolean isAsc
//    ) {
//
//        Map<String, Object> newsResponseDtoList = newsService.getNewsByCategory(
//                category,
//                page - 1,
//                size,
//                sortBy,
//                isAsc
//        );
//
//        return ResponseEntity.ok(newsResponseDtoList);
//    }
//
//    @GetMapping("/search")
//    public ResponseEntity<Map<String, Object>> SearchNews(
//            @RequestParam("keyword") String keyword,
//            @RequestParam("page") int page,
//            @RequestParam("size") int size
//    ) {
//
//        Map<String, Object> newsResponseDtoList = newsService.SearchNews(
//                keyword,
//                page - 1,
//                size
//        );
//
//        return ResponseEntity.ok(newsResponseDtoList);
//    }
//
//    @GetMapping("/search/basic")
//    public ResponseEntity<Map<String, Object>> SearchNewsBaSic(
//            @RequestParam("keyword") String keyword,
//            @RequestParam("page") int page,
//            @RequestParam("size") int size,
//            @RequestParam("sortBy") String sortBy,
//            @RequestParam("isAsc") boolean isAsc
//    ){
//
//        Map<String, Object> newsResponseDtoList = newsService.SearchNewsBaSic(
//                keyword,
//                page - 1,
//                size,
//                sortBy,
//                isAsc
//        );
//
//        return ResponseEntity.ok(newsResponseDtoList);
//    }
//
//    @GetMapping("/{newsId}")
//    public ResponseEntity<NewsDetailsResponseDto> getNewsDetails(@PathVariable Long newsId) {
//        NewsDetailsResponseDto newsDetails = newsService.getNewsDetails(newsId);
//
//        return ResponseEntity.ok(newsDetails);
//    }
//}

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Slf4j
public class PostsController {

    private final PostsService postsService;

    @GetMapping()
    public ResponseEntity<List<Object>> getPosts(@RequestParam(name = "page", defaultValue = "1") int page,
                                                 @RequestParam(name = "size", defaultValue = "10") int size,
                                                 @RequestParam(name = "sortBy", defaultValue = "createdDate") String sortBy,
                                                 @RequestParam(name = "isAsc", defaultValue = "true") boolean isAsc) {

        List<Object> postsResponseDtoList = postsService.getPosts(
                page - 1,
                size,
                sortBy,
                isAsc
        );

        return ResponseEntity.ok(postsResponseDtoList);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Object>> getPostsByCategory(
            @RequestParam("category") String category,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc) {

        List<Object> postsResponseDtoList = postsService.getPostsByCategory(
                category,
                page - 1,
                size,
                sortBy,
                isAsc
        );

        return ResponseEntity.ok(postsResponseDtoList);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Object>> searchPosts(
            @RequestParam("keyword") String keyword,
            @RequestParam("page") int page,
            @RequestParam("size") int size) {

        List<Object> postsResponseDtoList = postsService.searchPosts(
                keyword,
                page - 1,
                size
        );

        return ResponseEntity.ok(postsResponseDtoList);
    }

    @GetMapping("/search/basic")
    public ResponseEntity<List<Object>> searchPostsBasic(
            @RequestParam("keyword") String keyword,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc) {

        List<Object> postsResponseDtoList = postsService.searchPostsBasic(
                keyword,
                page - 1,
                size,
                sortBy,
                isAsc
        );

        return ResponseEntity.ok(postsResponseDtoList);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostsDetailsResponseDto> getPostsDetails(@PathVariable Long newsId) {
        PostsDetailsResponseDto postsDetails = postsService.getPostsDetails(newsId);

        return ResponseEntity.ok(postsDetails);
    }
}