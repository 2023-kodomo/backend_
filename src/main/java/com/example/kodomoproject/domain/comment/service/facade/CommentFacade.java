package com.example.kodomoproject.domain.comment.service.facade;

import com.example.kodomoproject.domain.comment.entity.Comment;
import com.example.kodomoproject.domain.comment.exception.CommentNotFoundException;
import com.example.kodomoproject.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentFacade {
    private final CommentRepository commentRepository;

    public Comment getCommentById(String id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);
    }

    public List<Comment> getCommentByProductId(String id) {
        return commentRepository.findByProductId(id);
    }
}
