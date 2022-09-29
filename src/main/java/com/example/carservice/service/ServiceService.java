package com.example.carservice.service;

import com.example.carservice.dto.ServiceRequestDto;
import com.example.carservice.dto.ServiceResponseDto;

public interface ServiceService extends Service<ServiceResponseDto, ServiceRequestDto, Long> {
}