package com.vdev.controller;


import com.vdev.dto.UserRegistrationRequest;
import com.vdev.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void registerUser(
            @Valid @RequestBody UserRegistrationRequest request) {
        userService.registerUser(
                request.getUsername(),
                request.getPassword()
        );
    }
}
