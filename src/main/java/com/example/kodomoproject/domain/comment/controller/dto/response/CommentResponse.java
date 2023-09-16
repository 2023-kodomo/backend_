package com.example.kodomoproject.domain.comment.controller.dto.response;

import com.example.kodomoproject.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private User writer;

    private String content;

    private Date createdDate;

}
