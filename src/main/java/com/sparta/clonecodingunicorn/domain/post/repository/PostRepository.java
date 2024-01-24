package com.sparta.clonecodingunicorn.domain.post.repository;


import com.sparta.clonecodingunicorn.domain.post.entity.Category;
import com.sparta.clonecodingunicorn.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByCategoryOrderByDateDesc(Category category);
}
