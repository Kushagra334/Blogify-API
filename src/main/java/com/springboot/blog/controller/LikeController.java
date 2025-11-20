package com.springboot.blog.controller;

import com.springboot.blog.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/posts/{postId}/likes")
@Tag(name = "Likes API")
public class LikeController {

    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @Operation(summary = "Like a post",
            description = "Likes a specific post. Requires authentication.")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping
    public ResponseEntity<String> likePost(@PathVariable(value = "postId") Long postId) {
        likeService.likePost(postId);
        return new ResponseEntity<>("Post liked successfully", HttpStatus.OK);
    }

    @Operation(summary = "Unlike a post",
            description = "Unlikes a specific post. Requires authentication.")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping
    public ResponseEntity<String> unlikePost(@PathVariable(value = "postId") Long postId) {
        likeService.unlikePost(postId);
        return new ResponseEntity<>("Post unliked successfully", HttpStatus.OK);
    }

    @Operation(summary = "Get likes count for a post",
            description = "Retrieves the total number of likes for a specific post.")
    @GetMapping("/count")
    public ResponseEntity<Map<String, Object>> getLikesCount(@PathVariable(value = "postId") Long postId) {
        long likesCount = likeService.getLikesCount(postId);
        Map<String, Object> response = new HashMap<>();
        response.put("postId", postId);
        response.put("likes", likesCount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
