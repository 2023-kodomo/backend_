package com.example.kodomoproject.domain.comment.controller.dto.response;

import com.example.kodomoproject.domain.comment.entity.Comment;
import com.example.kodomoproject.domain.user.entity.User;
import lombok.Builder;

import java.util.Date;

@Builder
public record CommentResponse(String id, User writer, String content, Date createdDate) {
    public CommentResponse(Comment comment) {
        this(comment.getId(), comment.getWriter(), comment.getContent(), comment.getCreatedDate());
    }

}
