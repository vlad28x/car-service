package com.aston.carservice.controller;

import com.aston.carservice.dto.UserResponseDto;
import com.aston.carservice.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final UserService userService;

    public EmployeeController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PatchMapping("/payment")
    List<UserResponseDto> paySalariesToCurrentCarServiceEmployees(Principal principal) {
        return userService.paySalariesToCurrentCarServiceEmployees(principal);
    }

}
