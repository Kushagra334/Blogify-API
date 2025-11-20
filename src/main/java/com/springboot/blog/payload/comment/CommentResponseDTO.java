package com.springboot.blog.payload.comment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponseDTO {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private Long postId;
    private Long userId;
}
