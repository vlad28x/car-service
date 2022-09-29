package com.example.carservice.service.carService;

import com.example.carservice.entity.CarService;
import com.example.carservice.repositories.CarServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CarService_ServiceImpl implements CarService_Service {

    @Autowired
    CarServiceRepository carServiceRepository;


    @Override
    public List<CarService> getAllCarServices() {
        return carServiceRepository.findAll();
    }

    @Override
    public void saveCarService(CarService carService) {
        carServiceRepository.save(carService);
    }

    @Override
    public CarService getCarService(long id) {
        CarService carService = new CarService();
        Optional<CarService> optional = carServiceRepository.findById(id);
        if (optional.isPresent()) {
            carService = optional.get();
        }
        return carService;
    }

    @Override
    public void deleteCarService(long id) {

    }
}
