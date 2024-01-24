package com.sparta.clonecodingunicorn.domain.post.dto;

import com.sparta.clonecodingunicorn.domain.post.entity.Category;
import lombok.Getter;

@Getter
public class PostRequestDto {
    private String title;
    private String contents;
    private String date;
    private String imageUrl;
    private Category category;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
    public void setDate(String date) {
        this.date=date;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}


