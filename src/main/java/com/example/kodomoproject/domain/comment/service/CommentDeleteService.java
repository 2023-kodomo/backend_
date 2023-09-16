package com.example.kodomoproject.domain.comment.service;

import com.example.kodomoproject.domain.comment.entity.Comment;
import com.example.kodomoproject.domain.comment.repository.CommentRepository;
import com.example.kodomoproject.domain.comment.service.facade.CommentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentDeleteService {
    private final CommentRepository commentRepository;
    private final CommentFacade commentFacade;

    public void deleteById(String id) {
        Comment comment = commentFacade.getCommentById(id);
        commentRepository.deleteById(comment.getId());
    }

    public void deleteByProductId(String id) {
        List<Comment> comments = commentFacade.getCommentByProductId(id);
        commentRepository.deleteAll(comments);
    }


}
