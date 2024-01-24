package com.sparta.clonecodingunicorn.domain.posts.repository;

import com.sparta.clonecodingunicorn.domain.posts.entity.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    Page<Posts> findAllByCategory(String category, Pageable pageable);

    @Query("select n FROM Posts n WHERE n.title like %:keyword% or n.contents like %:keyword% or n.category like %:keyword%")
    Page<Posts> searchNewsByKeyWord(@Param("keyword") String keyword, Pageable pageable);

    @Query(
            value =
                    "SELECT * FROM posts WHERE MATCH(title,contents,category) AGAINST (:keyword IN BOOLEAN MODE) "+
                            "LIMIT :limit OFFSET :offset", nativeQuery = true
    )
    List<Posts> fullTextSearchNewsByKeyWordNativeVer(
            @Param("keyword") String keyword,
            @Param("limit") int limit,
            @Param("offset") int offset
    );

    @Query(
            value = "SELECT COUNT(*) FROM posts WHERE MATCH(title,contents,category) AGAINST (:keyword IN BOOLEAN MODE)",
            nativeQuery = true
    )
    int countSearchNewsByKeyWordNativeVer(
            @Param("keyword") String keyword
    );

    Posts findTopByOrderByIdDesc();
}
