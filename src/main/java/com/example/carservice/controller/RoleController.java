package com.example.carservice.controller;

import com.example.carservice.dto.RoleRequestDto;
import com.example.carservice.dto.RoleResponseDto;
import com.example.carservice.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/{id}")
    public RoleResponseDto getById(@PathVariable("id") Long id) {
        return roleService.getById(id);
    }

    @GetMapping
    public List<RoleResponseDto> getAll() {
        return roleService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponseDto create(@RequestBody RoleRequestDto newRole) {
        return roleService.create(newRole);
    }

    @PutMapping("/{id}")
    public RoleResponseDto update(@PathVariable("id") Long id, @RequestBody RoleRequestDto newRole) {
        return roleService.update(id, newRole);
    }

    @DeleteMapping("/{id}")
    public void delete(Long id) {
        roleService.delete(id);
    }

}
