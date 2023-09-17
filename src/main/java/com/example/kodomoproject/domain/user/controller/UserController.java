package com.example.kodomoproject.domain.user.controller;

import com.example.kodomoproject.domain.user.controller.dto.UserResponse;
import com.example.kodomoproject.domain.user.service.GetUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final GetUserService getUserService;

    @GetMapping
    public UserResponse getMyInfo() {
        return getUserService.myself();
    }

    @GetMapping("{id}")
    public UserResponse getOtherUserInfo(@PathVariable String id) {
        return getUserService.otherUser(id);
    }

}
