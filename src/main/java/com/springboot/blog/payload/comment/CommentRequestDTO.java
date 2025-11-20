package com.springboot.blog.payload.comment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentRequestDTO {
    @NotEmpty
    @Size(min = 5, message = "Comment content must be at least 5 characters")
    private String content;
}
