package com.example.carservice.service.serviceConsumable;

import com.example.carservice.entity.ServiceConsumable;
import com.example.carservice.entity.ServiceConsumableId;
import com.example.carservice.repositories.ServiceConsumableRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class Service_ConsumableServiceImpl implements Service_ConsumableService {

    @Autowired
    ServiceConsumableRepository serviceConsumableRepository;
    @Override
    public List<ServiceConsumable> getAllServiceConsumables() {
        return serviceConsumableRepository.findAll();
    }

    @Override
    public void saveServiceConsumable(ServiceConsumable serviceConsumable) {
        serviceConsumableRepository.save(serviceConsumable);
    }

    @Override
    public ServiceConsumable getServiceConsumable(ServiceConsumableId id) {
        ServiceConsumable serviceConsumable = new ServiceConsumable();
        Optional<ServiceConsumable> optional = serviceConsumableRepository.findById(id);
        if (optional.isPresent()) {
            serviceConsumable = optional.get();
        }
        return serviceConsumable;
    }

    @Override
    public void deleteServiceConsumable(ServiceConsumableId id) {

    }
}
