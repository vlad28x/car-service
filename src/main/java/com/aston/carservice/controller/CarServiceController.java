package com.aston.carservice.controller;

import com.aston.carservice.dto.CarServiceRequestDto;
import com.aston.carservice.dto.CarServiceResponseDto;
import com.aston.carservice.service.CarServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/carservices")
public class CarServiceController {

    private final CarServiceService carService;

    public CarServiceController(CarServiceService carService) {
        this.carService = carService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public CarServiceResponseDto getById(@PathVariable("id") @Min(1) Long id) {
        return carService.getById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<CarServiceResponseDto> getAll() {
        return carService.getAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarServiceResponseDto create(@Valid @RequestBody CarServiceRequestDto newCarService) {
        return carService.create(newCarService);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public CarServiceResponseDto update(@PathVariable("id") @Min(1) Long id, @Valid @RequestBody CarServiceRequestDto newCarService) {
        return carService.update(id, newCarService);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") @Min(1) Long id) {
        carService.delete(id);
    }

}
