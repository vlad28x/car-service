package com.aston.carservice.controller;

import com.aston.carservice.dto.AuthenticationRequestDto;
import com.aston.carservice.dto.UserResponseDto;
import com.aston.carservice.exception.BadRequestException;
import com.aston.carservice.security.JwtTokenProvider;
import com.aston.carservice.service.UserService;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @PostMapping("/login")
    public String authenticate(@RequestBody AuthenticationRequestDto request) {
        try {
            UserResponseDto user = userService.findByUsername(request.getUsername());
            return jwtTokenProvider.createToken(request.getUsername(), user.getRole().getName());
        } catch (AuthenticationException e) {
            throw new BadRequestException("Invalid email/password combination");
        }
    }

}
