package com.example.carservice.service.carService;

import com.example.carservice.entity.CarService;

import java.util.List;

public interface CarService_Service {

    public List<CarService> getAllCarServices();

    public void saveCarService(CarService carService);

    public CarService getCarService(long id);

    public void deleteCarService(long id);
}
