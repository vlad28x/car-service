package com.aston.carservice.controller;

import com.aston.carservice.dto.ServiceConsumableRequestDto;
import com.aston.carservice.dto.ServiceConsumableResponseDto;
import com.aston.carservice.entity.ServiceConsumableId;
import com.aston.carservice.service.ServiceConsumableService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceConsumableController {

    private final ServiceConsumableService serviceConsumableService;

    public ServiceConsumableController(ServiceConsumableService serviceConsumableService) {
        this.serviceConsumableService = serviceConsumableService;
    }

    @GetMapping("/{serviceId}/consumables/{consumableId}")
    public ServiceConsumableResponseDto getById(@PathVariable("serviceId") Long serviceId,
                                                @PathVariable("consumableId") Long consumableId) {
        ServiceConsumableId id = new ServiceConsumableId();
        id.setServiceId(serviceId);
        id.setConsumableId(consumableId);
        return serviceConsumableService.getById(id);
    }

    @GetMapping("/consumables")
    public List<ServiceConsumableResponseDto> getAll() {
        return serviceConsumableService.getAll();
    }

    @PostMapping("/consumables")
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceConsumableResponseDto create(@RequestBody ServiceConsumableRequestDto newServiceConsumable) {
        return serviceConsumableService.create(newServiceConsumable);
    }

    @PutMapping("/{serviceId}/consumables/{consumableId}")
    public ServiceConsumableResponseDto update(@PathVariable("serviceId") Long serviceId,
                                               @PathVariable("consumableId") Long consumableId,
                                               @RequestBody ServiceConsumableRequestDto newServiceConsumable) {
        ServiceConsumableId id = new ServiceConsumableId();
        id.setServiceId(serviceId);
        id.setConsumableId(consumableId);
        return serviceConsumableService.update(id, newServiceConsumable);
    }

    @DeleteMapping("/{serviceId}/consumables/{consumableId}")
    public void delete(@PathVariable("serviceId") Long serviceId,
                       @PathVariable("consumableId") Long consumableId) {
        ServiceConsumableId id = new ServiceConsumableId();
        id.setServiceId(serviceId);
        id.setConsumableId(consumableId);
        serviceConsumableService.delete(id);
    }

}
