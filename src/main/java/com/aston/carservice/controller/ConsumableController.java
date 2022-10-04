package com.aston.carservice.controller;

import com.aston.carservice.dto.ConsumableRequestDto;
import com.aston.carservice.dto.ConsumableResponseDto;
import com.aston.carservice.service.ConsumableService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumables")
public class ConsumableController {

    private final ConsumableService consumableService;

    public ConsumableController(ConsumableService consumableService) {
        this.consumableService = consumableService;
    }

    @GetMapping("/{id}")
    public ConsumableResponseDto getById(@PathVariable("id") Long id) {
        return consumableService.getById(id);
    }

    @GetMapping
    public List<ConsumableResponseDto> getAll() {
        return consumableService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsumableResponseDto create(@RequestBody ConsumableRequestDto newConsumable) {
        return consumableService.create(newConsumable);
    }

    @PutMapping("/{id}")
    public ConsumableResponseDto update(@PathVariable("id") Long id, @RequestBody ConsumableRequestDto newConsumable) {
        return consumableService.update(id, newConsumable);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        consumableService.delete(id);
    }

}
