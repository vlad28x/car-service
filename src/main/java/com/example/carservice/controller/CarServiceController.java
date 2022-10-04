package com.example.carservice.controller;

import com.example.carservice.dto.CarServiceRequestDto;
import com.example.carservice.dto.CarServiceResponseDto;
import com.example.carservice.service.CarServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car_service")
public class CarServiceController {

    private final CarServiceService carService;

    @Autowired
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
