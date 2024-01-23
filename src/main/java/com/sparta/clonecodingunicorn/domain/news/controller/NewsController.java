package com.sparta.clonecodingunicorn.domain.news.controller;

import com.sparta.clonecodingunicorn.domain.news.dto.NewsDetailsResponseDto;
import com.sparta.clonecodingunicorn.domain.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
@RequestMapping("/api/news")
@RequiredArgsConstructor
@Slf4j
public class NewsController {

    private final NewsService newsService;

    @GetMapping()
    public ResponseEntity<List<Object>> getNews(@RequestParam(name = "page", defaultValue = "1") int page,
                                                @RequestParam(name = "size", defaultValue = "10") int size,
                                                @RequestParam(name = "sortBy", defaultValue = "createdDate") String sortBy,
                                                @RequestParam(name = "isAsc", defaultValue = "true") boolean isAsc) {

        List<Object> newsResponseDtoList = newsService.getNews(
                page - 1,
                size,
                sortBy,
                isAsc
        );

        return ResponseEntity.ok(newsResponseDtoList);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Object>> getNewsByCategory(
            @RequestParam("category") String category,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc) {

        List<Object> newsResponseDtoList = newsService.getNewsByCategory(
                category,
                page - 1,
                size,
                sortBy,
                isAsc
        );

        return ResponseEntity.ok(newsResponseDtoList);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Object>> searchNews(
            @RequestParam("keyword") String keyword,
            @RequestParam("page") int page,
            @RequestParam("size") int size) {

        List<Object> newsResponseDtoList = newsService.searchNews(
                keyword,
                page - 1,
                size
        );

        return ResponseEntity.ok(newsResponseDtoList);
    }

    @GetMapping("/search/basic")
    public ResponseEntity<List<Object>> searchNewsBasic(
            @RequestParam("keyword") String keyword,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc) {

        List<Object> newsResponseDtoList = newsService.searchNewsBasic(
                keyword,
                page - 1,
                size,
                sortBy,
                isAsc
        );

        return ResponseEntity.ok(newsResponseDtoList);
    }

    @GetMapping("/{newsId}")
    public ResponseEntity<NewsDetailsResponseDto> getNewsDetails(@PathVariable Long newsId) {
        NewsDetailsResponseDto newsDetails = newsService.getNewsDetails(newsId);

        return ResponseEntity.ok(newsDetails);
    }
}