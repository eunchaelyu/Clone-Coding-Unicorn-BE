package com.sparta.clonecodingunicorn.domain.posts.dto;

import com.sparta.clonecodingunicorn.domain.posts.entity.Posts;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PostsResponseDto {

    private Long id;

    private String title;

    private LocalDate date;

    private String category;

    private String imageUrl;


    public PostsResponseDto(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.date = posts.getNewsDate();
        this.category = posts.getCategory();
        this.imageUrl = posts.getImgUrl();
    }
}
