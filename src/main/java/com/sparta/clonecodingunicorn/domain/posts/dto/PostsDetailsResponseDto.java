package com.sparta.clonecodingunicorn.domain.posts.dto;

import com.sparta.clonecodingunicorn.domain.posts.entity.Posts;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PostsDetailsResponseDto {

    private String title;

    private String contents;

    private LocalDate date;

    private String category;

    private String imageUrl;

    private int heart;

    public PostsDetailsResponseDto(Posts posts) {
        this.title = posts.getTitle();
        this.contents = posts.getContent();
        this.date = posts.getNewsDate();
        this.category = posts.getCategory();
        this.imageUrl = posts.getImgUrl();
        this.heart = posts.getHeartCount();
    }
}
