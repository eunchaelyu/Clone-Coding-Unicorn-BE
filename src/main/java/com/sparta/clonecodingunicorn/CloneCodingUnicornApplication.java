package com.sparta.clonecodingunicorn;

import com.sparta.clonecodingunicorn.domain.post.service.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloneCodingUnicornApplication{
//    public class CloneCodingUnicornApplication implements CommandLineRunner{
    private PostService postService;
    public CloneCodingUnicornApplication(PostService postService) {
        this.postService = postService;
    }
    public static void main(String[] args) {
        SpringApplication.run(CloneCodingUnicornApplication.class, args);
    }
//    @Override
//    public void run(String... args) throws Exception {
//        postService.scrapeNaverNews("정치");
//        postService.scrapeNaverNews("경제");
//        postService.scrapeNaverNews("세계");
//        postService.scrapeNaverNews("테크");
//        postService.scrapeNaverNews("노동");
//        postService.scrapeNaverNews("환경");
//        postService.scrapeNaverNews("인권");
//        postService.scrapeNaverNews("사회");
//        postService.scrapeNaverNews("문화");
//        postService.scrapeNaverNews("라이프");
//
//    }
}
