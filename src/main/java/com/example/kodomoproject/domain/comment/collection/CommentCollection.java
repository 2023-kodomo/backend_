package com.example.kodomoproject.domain.comment.collection;

import com.example.kodomoproject.domain.comment.controller.dto.response.CommentResponse;
import com.example.kodomoproject.domain.comment.entity.Comment;

import java.util.List;

public class CommentCollection {

    private final List<Comment> comments;

    public CommentCollection(List<Comment> comments) {
        this.comments = comments;
    }

    public List<CommentResponse> toCommentResponse() {
        return comments.stream()
                .map(CommentResponse::new)
                .toList();
    }

}
