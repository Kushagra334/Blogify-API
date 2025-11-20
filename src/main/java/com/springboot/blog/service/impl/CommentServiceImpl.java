package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.entity.User;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.comment.CommentRequestDTO;
import com.springboot.blog.payload.comment.CommentResponseDTO;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.repository.UserRepository;
import com.springboot.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;
    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentResponseDTO createComment(Long postId, Long userId, CommentRequestDTO dto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Comment comment = mapToEntity(dto);
        comment.setPost(post);
        comment.setUser(user);

        Comment newComment = commentRepository.save(comment);
        return mapToDTO(newComment);
    }

    @Override
    public List<CommentResponseDTO> getCommentsByPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentResponseDTO updateComment(Long commentId, CommentRequestDTO dto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        // Authorization check
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = userRepository.findByUsernameOrEmail(currentUsername, currentUsername)
                .orElseThrow(() -> new BlogAPIException(HttpStatus.UNAUTHORIZED, "User not found."));

        if (!comment.getUser().getId().equals(currentUser.getId()) && !currentUser.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"))) {
            throw new BlogAPIException(HttpStatus.UNAUTHORIZED, "Access denied. Only owner or admin can update this comment.");
        }

        comment.setContent(dto.getContent());
        Comment updatedComment = commentRepository.save(comment);
        return mapToDTO(updatedComment);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        // Authorization check
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = userRepository.findByUsernameOrEmail(currentUsername, currentUsername)
                .orElseThrow(() -> new BlogAPIException(HttpStatus.UNAUTHORIZED, "User not found."));

        if (!comment.getUser().getId().equals(currentUser.getId()) && !currentUser.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"))) {
            throw new BlogAPIException(HttpStatus.UNAUTHORIZED, "Access denied. Only owner or admin can delete this comment.");
        }

        commentRepository.delete(comment);
    }

    private CommentResponseDTO mapToDTO(Comment comment) {
        CommentResponseDTO commentResponseDTO = mapper.map(comment, CommentResponseDTO.class);
        commentResponseDTO.setPostId(comment.getPost().getId());
        commentResponseDTO.setUserId(comment.getUser().getId());
        return commentResponseDTO;
    }

    private Comment mapToEntity(CommentRequestDTO commentRequestDTO) {
        return mapper.map(commentRequestDTO, Comment.class);
    }
}
