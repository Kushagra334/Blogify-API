package com.springboot.blog.repository;

import com.springboot.blog.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByCategoryId(Long categoryId);
    Page<Post> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
    Page<Post> findByCategory_Name(String categoryName, Pageable pageable);
    Page<Post> findByUser_Id(Long userId, Pageable pageable);
}
