package com.sparta.clonecodingunicorn.domain.post.service;

import com.sparta.clonecodingunicorn.domain.post.dto.PostRequestDto;
import com.sparta.clonecodingunicorn.domain.post.dto.PostResponseDto;
import com.sparta.clonecodingunicorn.domain.post.entity.Category;
import com.sparta.clonecodingunicorn.domain.post.entity.Post;
import com.sparta.clonecodingunicorn.domain.post.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

//    public PostResponseDto createPost(PostRequestDto requestDto) { //포스트 작성
//        Post savedPost = postRepository.save(new Post(requestDto));
//        return new PostResponseDto(savedPost);
//    }

//public List<PostResponseDto> createPost() {
//    String url = "https://search.naver.com/search.naver";
//    List<Post> scrapedPosts = scrapeAndSavePosts(url);
//
//    if (!scrapedPosts.isEmpty()) {
//        List<PostResponseDto> savedPostsResponseDto = new ArrayList<>();
//        for (Post scrapedPost : scrapedPosts) {
//            Post savedPost = postRepository.save(scrapedPost);
//            savedPostsResponseDto.add(new PostResponseDto(savedPost));
//        }
//        return savedPostsResponseDto;
//    } else {
//        return Collections.emptyList();
//    }
//}

    public List<PostResponseDto> getPostList() { //포스트 전체 조회
        List<Post> postList = postRepository.findAll();

        List<PostResponseDto> postResponseList = postList.stream().map(PostResponseDto::new).toList();
        return postResponseList;
    }

    @Transactional
    public PostResponseDto updatePost(PostRequestDto requestDto, Long postId) { //포스트 수정
        Post post = postRepository.findById(postId)
                .orElseThrow(IllegalArgumentException::new);

        post.updatePost(requestDto);
        return new PostResponseDto(post);
    }

    public PostResponseDto deletePost(Long postId) { //포스트 삭제
        Post post = postRepository.findById(postId)
                .orElseThrow(IllegalArgumentException::new);
        postRepository.delete(post);
        return new PostResponseDto(post);
    }

    public List<PostResponseDto> getSortedPostList(Category category) { //카테고리별 포스트 조회
        List<Post> sortedPost = postRepository.findAllByCategoryOrderByDateDesc(Category.valueOf(String.valueOf(category))); // 최신순으로 정렬
        List<PostResponseDto> sortedPostList = sortedPost.stream().map(PostResponseDto::new).toList();
        return sortedPostList;

    }

//    private List<Post> scrapeAndSavePosts(String url) {
//        List<Post> scrapedPosts = new ArrayList<>();
//
//        try {
//            Document document = Jsoup.connect(url).get(); //url로 부터 HTML 문서 가져오기
//            Elements postElements = document.select("div.news_area");
//
//            for (Element postElement : postElements) {
//                String title = postElement.select(".article_head.art_tit").text();
//                String contents = postElement.selectFirst("div#article p").text();
//                String imageUrl = postElement.selectFirst("img").attr("src");
//                String date = postElement.select("span.info").text();
//                String categoryText = postElement.select("a.info.press").text();
//
//                if (!categoryText.isEmpty()) {
//                    Category category = mapCategory(categoryText);
//
//                    PostRequestDto requestDto = new PostRequestDto();
//                    requestDto.setTitle(title);
//                    requestDto.setContents(contents);
//                    requestDto.setDate(date);
//                    requestDto.setImageUrl(imageUrl);
//                    requestDto.setCategory(category);
//
//                    Post savedPost = postRepository.save(new Post(requestDto));
//                    scrapedPosts.add(savedPost);
//                }
//            }
//        } catch (IOException | java.io.IOException e) {
//            log.error(e.getMessage(), e);
//        }
//        return scrapedPosts;
//    }
//


    public void scrapeNaverNews(String query) throws IOException {
        String url = "https://search.naver.com/search.naver?query=" + query + "&where=news";


        try {
            Document document = Jsoup.connect(url).get();
            Elements newsArticles = document.select("div.news_area");

            for (Element article : newsArticles) {
                String title = article.select("a.news_tit").text();
                String contents = article.select("div.news_dsc").text();
                String imageUrl = article.select("a.dsc_thumb img").attr("src");

                String date = article.select("span.info").text();

                    //카테고리 클래스에 있는 query인지 확인
                    String categoryText = query;
                    Category category = mapCategory(categoryText);

                    //새로운 PostResponseDto 리스트 선언 및 생성
                    List<PostResponseDto> savedPostsResponseDto = new ArrayList<>();
                    //Post 객체로 하나씩 DB에 저장
                    Post savedPost = postRepository.save(new Post(title, contents, imageUrl, date, category));
                    //PostResponseDto에 추가
                    savedPostsResponseDto.add(new PostResponseDto(savedPost));

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Category mapCategory(String categoryText) {
        for (Category category : Category.values()) {
            if (categoryText.equals(category.name())) {
                return category;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 카테고리 입니다." + categoryText);
    }
}

