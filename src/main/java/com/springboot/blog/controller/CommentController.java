package com.springboot.blog.controller;

import com.springboot.blog.payload.comment.CommentRequestDTO;
import com.springboot.blog.payload.comment.CommentResponseDTO;
import com.springboot.blog.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Comments API")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "Create a new comment",
            description = "Creates a new comment for a specific post and user.")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponseDTO> createComment(
            @PathVariable(value = "postId") Long postId,
            @RequestParam(value = "userId") Long userId,
            @Valid @RequestBody CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO createdComment = commentService.createComment(postId, userId, commentRequestDTO);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all comments for a post",
            description = "Retrieves a list of all comments associated with a specific post.")
    @GetMapping("/posts/{postId}/comments")
    public List<CommentResponseDTO> getCommentsByPost(@PathVariable(value = "postId") Long postId) {
        return commentService.getCommentsByPost(postId);
    }

    @Operation(summary = "Update an existing comment",
            description = "Updates an existing comment. Only the comment owner or an Admin can perform this action.")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateComment(
            @PathVariable(value = "commentId") Long commentId,
            @Valid @RequestBody CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO updatedComment = commentService.updateComment(commentId, commentRequestDTO);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @Operation(summary = "Delete a comment",
            description = "Deletes a comment. Only the comment owner or an Admin can perform this action.")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
