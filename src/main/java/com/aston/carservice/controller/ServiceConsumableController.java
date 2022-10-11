package com.aston.carservice.controller;

import com.aston.carservice.dto.ServiceConsumableRequestDto;
import com.aston.carservice.dto.ServiceConsumableResponseDto;
import com.aston.carservice.entity.ServiceConsumableId;
import com.aston.carservice.service.ServiceConsumableService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/services")
public class ServiceConsumableController {

    private final ServiceConsumableService serviceConsumableService;

    public ServiceConsumableController(ServiceConsumableService serviceConsumableService) {
        this.serviceConsumableService = serviceConsumableService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{serviceId}/consumables/{consumableId}")
    public ServiceConsumableResponseDto getById(@PathVariable("serviceId") @Min(1) Long serviceId,
                                                @PathVariable("consumableId") @Min(1) Long consumableId) {
        ServiceConsumableId id = new ServiceConsumableId();
        id.setServiceId(serviceId);
        id.setConsumableId(consumableId);
        return serviceConsumableService.getById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/consumables")
    public List<ServiceConsumableResponseDto> getAll() {
        return serviceConsumableService.getAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/consumables")
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceConsumableResponseDto create(@Valid @RequestBody ServiceConsumableRequestDto newServiceConsumable) {
        return serviceConsumableService.create(newServiceConsumable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{serviceId}/consumables/{consumableId}")
    public ServiceConsumableResponseDto update(@PathVariable("serviceId") @Min(1) Long serviceId,
                                               @PathVariable("consumableId") @Min(1) Long consumableId,
                                               @Valid @RequestBody ServiceConsumableRequestDto newServiceConsumable) {
        ServiceConsumableId id = new ServiceConsumableId();
        id.setServiceId(serviceId);
        id.setConsumableId(consumableId);
        return serviceConsumableService.update(id, newServiceConsumable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{serviceId}/consumables/{consumableId}")
    public void delete(@PathVariable("serviceId") @Min(1) Long serviceId,
                       @PathVariable("consumableId") @Min(1) Long consumableId) {
        ServiceConsumableId id = new ServiceConsumableId();
        id.setServiceId(serviceId);
        id.setConsumableId(consumableId);
        serviceConsumableService.delete(id);
    }

}
