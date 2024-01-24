package com.sparta.clonecodingunicorn.domain.posts.entity;

import com.sparta.clonecodingunicorn.domain.posts.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Posts extends Timestamped {

    @jakarta.persistence.Id
    @GeneratedValue
    private Long Id;

    @Column
    private String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private String imgUrl;

    @Column
    private LocalDate newsDate;

    @Column
    private String category;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String newsSummary;

    @Column
    private int heartCount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    public Posts(String title, String content, String imgUrl, LocalDate NewsDate, String category, String newsSummary) {

        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
        this.newsDate = NewsDate;
        this.category = category;
        this.newsSummary = newsSummary;

    }

    public void increaseHeartCount() {
        this.heartCount += 1;
    }
    public void decreaseHeartCount() {
        this.heartCount -= 1;
    }

}
