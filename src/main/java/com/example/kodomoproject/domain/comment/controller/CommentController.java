package com.example.kodomoproject.domain.comment.controller;

import com.example.kodomoproject.domain.comment.controller.dto.request.CommentRequest;
import com.example.kodomoproject.domain.comment.service.CommentCreateService;
import com.example.kodomoproject.domain.comment.service.CommentDeleteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentCreateService createService;
    private final CommentDeleteService deleteService;
    @PostMapping
    public void create(@Valid @RequestBody CommentRequest request) {
        createService.execute(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        deleteService.deleteById(id);
    }

}
