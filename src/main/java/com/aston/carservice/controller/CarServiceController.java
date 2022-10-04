package com.aston.carservice.controller;

import com.aston.carservice.dto.CarServiceRequestDto;
import com.aston.carservice.dto.CarServiceResponseDto;
import com.aston.carservice.service.CarServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carservices")
public class CarServiceController {

    private final CarServiceService carService;

    public CarServiceController(CarServiceService carService) {
        this.carService = carService;
    }

    @GetMapping("/{id}")
    public CarServiceResponseDto getById(@PathVariable("id") Long id) {
        return carService.getById(id);
    }

    @GetMapping
    public List<CarServiceResponseDto> getAll() {
        return carService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarServiceResponseDto create(@RequestBody CarServiceRequestDto newCarService) {
        return carService.create(newCarService);
    }

    @PutMapping("/{id}")
    public CarServiceResponseDto update(@PathVariable("id") Long id, @RequestBody CarServiceRequestDto newCarService) {
        return carService.update(id, newCarService);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        carService.delete(id);
    }

}
