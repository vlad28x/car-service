package com.example.carservice.controller;

import com.example.carservice.dto.UserRequestDto;
import com.example.carservice.dto.UserResponseDto;
import com.example.carservice.service.UserService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserResponseDto getById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto create(@RequestBody UserRequestDto newUser) {
        return userService.create(newUser);
    }

    @PutMapping("/{id}")
    public UserResponseDto update(@PathVariable("id") Long id, @RequestBody UserRequestDto newUser) {
        return userService.update(id, newUser);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }

}
