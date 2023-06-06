package com.springboot.blog.repository;

import com.springboot.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByCategoryId(Long categoryId);


//    @Query("SELECT p FROM posts p WHERE " +
//            "p.name LIKE CONCAT('%',:query, '%)" +
//            "p.body LIKE CONCAT(('%',:query, '%)")
//    List<Post> searchPosts(String query);
}
