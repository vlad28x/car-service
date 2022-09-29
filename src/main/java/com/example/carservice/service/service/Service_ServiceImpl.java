package com.example.carservice.service.service;

import com.example.carservice.entity.Service;
import com.example.carservice.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class Service_ServiceImpl implements Service_Service {

    @Autowired
    ServiceRepository serviceRepository;


    @Override
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public void saveService(Service service) {
        serviceRepository.save(service);
    }

    @Override
    public Service getService(long id) {
        Service service = new Service();
        Optional<Service> optional = serviceRepository.findById(id);
        if (optional.isPresent()) {
            service = optional.get();
        }
        return service;
    }

    @Override
    public void deleteService(long id) {
        serviceRepository.deleteById(id);
    }
}
