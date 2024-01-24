package com.sparta.clonecodingunicorn.domain.news.dto;

import com.sparta.clonecodingunicorn.domain.news.entity.News;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class NewsDetailsResponseDto {

    private String title;

    private String contents;

    private LocalDate date;

    private String category;

    private String imageUrl;

    private int heart;

    public NewsDetailsResponseDto(News news) {
        this.title = news.getTitle();
        this.contents = news.getContent();
        this.date = news.getNewsDate();
        this.category = news.getCategory();
        this.imageUrl = news.getImgUrl();
        this.heart = news.getHeartCount();
    }
}
