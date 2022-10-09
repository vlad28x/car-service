package com.aston.carservice.controller;

import com.aston.carservice.dto.ConsumableRequestDto;
import com.aston.carservice.dto.ConsumableResponseDto;
import com.aston.carservice.service.ConsumableService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/consumables")
public class ConsumableController {

    private final ConsumableService consumableService;

    public ConsumableController(ConsumableService consumableService) {
        this.consumableService = consumableService;
    }

    @GetMapping("/{id}")
    public ConsumableResponseDto getById(@PathVariable("id") @Min(1) Long id) {
        return consumableService.getById(id);
    }

    @GetMapping
    public List<ConsumableResponseDto> getAll() {
        return consumableService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsumableResponseDto create(@Valid @RequestBody ConsumableRequestDto newConsumable) {
        return consumableService.create(newConsumable);
    }

    @PutMapping("/{id}")
    public ConsumableResponseDto update(@PathVariable("id") @Min(1) Long id, @Valid @RequestBody ConsumableRequestDto newConsumable) {
        return consumableService.update(id, newConsumable);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") @Min(1) Long id) {
        consumableService.delete(id);
    }

}
