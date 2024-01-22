package com.sparta.clonecodingunicorn.domain.post.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column
    private String title;

    @Column
    private String contents;

    @Column
    private LocalDateTime createdAt;

    @Column
    private String imageUrl;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Category category;
}