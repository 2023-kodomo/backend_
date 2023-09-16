package com.example.kodomoproject.domain.comment.service;

import com.example.kodomoproject.domain.comment.controller.dto.request.CommentUpdateRequest;
import com.example.kodomoproject.domain.comment.entity.Comment;
import com.example.kodomoproject.domain.comment.repository.CommentRepository;
import com.example.kodomoproject.domain.comment.service.facade.CommentFacade;
import com.example.kodomoproject.global.facade.DateFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CommentUpdateService {
    private final CommentRepository commentRepository;
    private final CommentFacade commentFacade;
    private final DateFacade dateFacade;

    public void execute(CommentUpdateRequest request) {
        Comment comment = commentFacade.getCommentById(request.getId());
        Date now = dateFacade.getNowDate();

        comment.update(request.getContent(), now);

        commentRepository.save(comment);
    }

}
