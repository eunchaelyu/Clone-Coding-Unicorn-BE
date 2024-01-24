package com.sparta.clonecodingunicorn.domain.post.entity;

import com.sparta.clonecodingunicorn.domain.post.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column
    private String title;

    @Column
    private String contents;

    @Column
    private String date;

    @Column
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column
    private Category category;


    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.imageUrl = requestDto.getImageUrl();
        this.category = requestDto.getCategory();
    }

    public Post(String title, String contents, String imageUrl, String date, Category category) {
        this.title = title;
        this.contents = contents;
        this.date = date;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public Post updatePost(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.date = requestDto.getDate();
        this.imageUrl = requestDto.getImageUrl();
        this.category = requestDto.getCategory();
        return this;
    }
}



