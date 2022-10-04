package com.example.carservice.controller;

import com.example.carservice.dto.ServiceRequestDto;
import com.example.carservice.dto.ServiceResponseDto;
import com.example.carservice.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceController {

    private final ServiceService service;

    @Autowired
    public ServiceController(ServiceService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ServiceResponseDto getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<ServiceResponseDto> getAll() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceResponseDto create(@RequestBody ServiceRequestDto newService) {
        return service.create(newService);
    }

    @PutMapping("/{id}")
    public ServiceResponseDto update(@PathVariable("id") Long id, @RequestBody ServiceRequestDto newService) {
        return service.update(id, newService);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

}
