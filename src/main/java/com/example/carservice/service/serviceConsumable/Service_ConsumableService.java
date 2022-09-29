package com.example.carservice.service.serviceConsumable;

import com.example.carservice.entity.ServiceConsumable;
import com.example.carservice.entity.ServiceConsumableId;

import java.util.List;

public interface Service_ConsumableService {

    public List<ServiceConsumable> getAllServiceConsumables();

    public void saveServiceConsumable(ServiceConsumable serviceConsumable);

    public ServiceConsumable getServiceConsumable(ServiceConsumableId id);

    public void deleteServiceConsumable(ServiceConsumableId id);
}
