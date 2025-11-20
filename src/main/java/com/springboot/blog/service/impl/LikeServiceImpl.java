package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.entity.PostLike;
import com.springboot.blog.entity.User;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.PostLikeRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.repository.UserRepository;
import com.springboot.blog.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeServiceImpl implements LikeService {

    private PostLikeRepository postLikeRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    public LikeServiceImpl(PostLikeRepository postLikeRepository, PostRepository postRepository, UserRepository userRepository) {
        this.postLikeRepository = postLikeRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void likePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        User currentUser = getCurrentUser();

        if (!postLikeRepository.existsByPostAndUser(post, currentUser)) {
            PostLike postLike = new PostLike();
            postLike.setPost(post);
            postLike.setUser(currentUser);
            postLikeRepository.save(postLike);
        }
    }

    @Override
    @Transactional
    public void unlikePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        User currentUser = getCurrentUser();

        postLikeRepository.findByPostAndUser(post, currentUser).ifPresent(postLike -> {
            postLikeRepository.delete(postLike);
        });
    }

    @Override
    public long getLikesCount(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        return postLikeRepository.countByPost(post);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        return userRepository.findByUsernameOrEmail(currentUsername, currentUsername)
                .orElseThrow(() -> new BlogAPIException(HttpStatus.UNAUTHORIZED, "User not found."));
    }
}
