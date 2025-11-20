package com.springboot.blog.service;

import com.springboot.blog.payload.comment.CommentRequestDTO;
import com.springboot.blog.payload.comment.CommentResponseDTO;

import java.util.List;

public interface CommentService {
    CommentResponseDTO createComment(Long postId, Long userId, CommentRequestDTO dto);
    List<CommentResponseDTO> getCommentsByPost(Long postId);
    CommentResponseDTO updateComment(Long commentId, CommentRequestDTO dto);
    void deleteComment(Long commentId);
}
