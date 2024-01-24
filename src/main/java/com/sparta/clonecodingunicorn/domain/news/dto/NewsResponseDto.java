package com.sparta.clonecodingunicorn.domain.news.dto;

import com.sparta.clonecodingunicorn.domain.news.entity.News;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class NewsResponseDto {

    private Long id;

    private String title;

    private LocalDate date;

    private String category;

    private String imageUrl;


    public NewsResponseDto(News news) {
        this.id = news.getId();
        this.title = news.getTitle();
        this.date = news.getNewsDate();
        this.category = news.getCategory();
        this.imageUrl = news.getImgUrl();
    }
}
