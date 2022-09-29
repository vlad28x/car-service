package com.example.carservice.service.service;

import com.example.carservice.entity.Service;
import com.example.carservice.entity.ServiceConsumable;
import com.example.carservice.entity.ServiceConsumableId;

import java.util.List;

public interface Service_Service  {


    public List<Service> getAllServices();

    public void saveService(Service service);

    public Service getService(long id);

    public void deleteService(long id);
}
