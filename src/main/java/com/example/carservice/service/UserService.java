package com.example.carservice.service;

import com.example.carservice.dto.UserRequestDto;
import com.example.carservice.dto.UserResponseDto;

public interface UserService extends Service<UserResponseDto, UserRequestDto, Long> {
}
