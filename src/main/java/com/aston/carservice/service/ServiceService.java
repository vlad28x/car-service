package com.aston.carservice.service;

import com.aston.carservice.dto.ServiceRequestDto;
import com.aston.carservice.dto.ServiceResponseDto;

public interface ServiceService extends Service<ServiceResponseDto, ServiceRequestDto, Long> {
}