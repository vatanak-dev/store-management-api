package com.vdev.controller;

import com.vdev.dto.LoginRequestDTO;
import com.vdev.enums.UserRole;
import com.vdev.security.CustomUserDetails;
import com.vdev.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }
    @PostMapping("/login")
    public String login(
            @Valid @RequestBody LoginRequestDTO loginRequestDTO){

       Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword()));

       CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        return jwtService.generateToken(authentication.getName(),
               customUserDetails.getUserRole());
    }
}
