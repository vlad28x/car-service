package com.aston.carservice.controller;

import com.aston.carservice.dto.UserRequestDto;
import com.aston.carservice.dto.UserResponseDto;
import com.aston.carservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserResponseDto getById(@PathVariable("id") @Min(1) Long id) {
        return userService.getById(id);
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto create(@Valid @RequestBody UserRequestDto newUser) {
        return userService.create(newUser);
    }


    @PutMapping("/{id}")
    public UserResponseDto update(@PathVariable("id") @Min(1) Long id, @Valid @RequestBody UserRequestDto newUser) {
        return userService.update(id, newUser);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") @Min(1) Long id) {
        userService.delete(id);
    }

}
