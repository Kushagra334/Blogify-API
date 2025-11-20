package com.springboot.blog.service;

public interface LikeService {
    void likePost(Long postId);
    void unlikePost(Long postId);
    long getLikesCount(Long postId);
}
