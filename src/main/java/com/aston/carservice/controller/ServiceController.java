package com.aston.carservice.controller;

import com.aston.carservice.dto.ServiceRequestDto;
import com.aston.carservice.dto.ServiceResponseDto;
import com.aston.carservice.service.ServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/services")
public class ServiceController {

    private final ServiceService service;

    public ServiceController(ServiceService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ServiceResponseDto getById(@PathVariable("id") @Min(1) Long id) {
        return service.getById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<ServiceResponseDto> getAll() {
        return service.getAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceResponseDto create(@Valid @RequestBody ServiceRequestDto newService) {
        return service.create(newService);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ServiceResponseDto update(@PathVariable("id") @Min(1) Long id, @Valid @RequestBody ServiceRequestDto newService) {
        return service.update(id, newService);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") @Min(1) Long id) {
        service.delete(id);
    }

}
