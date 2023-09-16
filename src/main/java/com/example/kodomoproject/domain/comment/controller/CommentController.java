package com.example.kodomoproject.domain.comment.controller;

import com.example.kodomoproject.domain.comment.controller.dto.request.CommentRequest;
import com.example.kodomoproject.domain.comment.controller.dto.request.CommentUpdateRequest;
import com.example.kodomoproject.domain.comment.service.CommentCreateService;
import com.example.kodomoproject.domain.comment.service.CommentDeleteService;
import com.example.kodomoproject.domain.comment.service.CommentUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentCreateService createService;
    private final CommentUpdateService updateService;
    private final CommentDeleteService deleteService;
    @PostMapping
    public void create(@RequestBody CommentRequest request) {
        createService.execute(request);
    }

    @PutMapping
    public void update(@RequestBody CommentUpdateRequest request) {
        updateService.execute(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        deleteService.deleteById(id);
    }

}
