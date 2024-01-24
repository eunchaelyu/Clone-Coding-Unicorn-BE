package com.sparta.clonecodingunicorn.domain.post.dto;

import com.sparta.clonecodingunicorn.domain.post.entity.Category;
import com.sparta.clonecodingunicorn.domain.post.entity.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private Long postId;
    private String title;
    private String contents;
    private String date;
    private String imageUrl;
    private Category category;

    public PostResponseDto(Post post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.date = post.getDate();
        this.imageUrl = post.getImageUrl();
        this.category = post.getCategory();

    }
}


