package com.aston.carservice.service;

import com.aston.carservice.dto.UserRequestDto;
import com.aston.carservice.dto.UserResponseDto;

public interface UserService extends Service<UserResponseDto, UserRequestDto, Long> {

    UserResponseDto findByUsername(String username);

}
