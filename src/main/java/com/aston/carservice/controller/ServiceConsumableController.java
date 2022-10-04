package com.aston.carservice.controller;

import com.aston.carservice.dto.ServiceConsumableRequestDto;
import com.aston.carservice.dto.ServiceConsumableResponseDto;
import com.aston.carservice.entity.ServiceConsumableId;
import com.aston.carservice.service.ServiceConsumableService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/consumables")
public class ServiceConsumableController {

    private final ServiceConsumableService serviceConsumableService;

    public ServiceConsumableController(ServiceConsumableService serviceConsumableService) {
        this.serviceConsumableService = serviceConsumableService;
    }

    @GetMapping("/{id}")
    public ServiceConsumableResponseDto getById(@PathVariable("id") ServiceConsumableId id) {
        return serviceConsumableService.getById(id);
    }

    @GetMapping
    public List<ServiceConsumableResponseDto> getAll() {
        return serviceConsumableService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceConsumableResponseDto create(@RequestBody ServiceConsumableRequestDto newServiceConsumable) {
        return serviceConsumableService.create(newServiceConsumable);
    }

    @PutMapping("/{id}")
    public ServiceConsumableResponseDto update(@PathVariable("id") ServiceConsumableId id,
                                               @RequestBody ServiceConsumableRequestDto newServiceConsumable) {
        return serviceConsumableService.update(id, newServiceConsumable);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") ServiceConsumableId id) {
        serviceConsumableService.delete(id);
    }

}
