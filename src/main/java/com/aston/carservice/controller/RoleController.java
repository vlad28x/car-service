package com.aston.carservice.controller;

import com.aston.carservice.dto.RoleRequestDto;
import com.aston.carservice.dto.RoleResponseDto;
import com.aston.carservice.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public RoleResponseDto getById(@PathVariable("id") @Min(1) Long id) {
        return roleService.getById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<RoleResponseDto> getAll() {
        return roleService.getAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponseDto create(@Valid @RequestBody RoleRequestDto newRole) {
        return roleService.create(newRole);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public RoleResponseDto update(@PathVariable("id") @Min(1) Long id, @Valid @RequestBody RoleRequestDto newRole) {
        return roleService.update(id, newRole);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") @Min(1) Long id) {
        roleService.delete(id);
    }

}
