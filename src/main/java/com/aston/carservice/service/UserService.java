package com.aston.carservice.service;

import com.aston.carservice.dto.UserRequestDto;
import com.aston.carservice.dto.UserResponseDto;

import java.security.Principal;
import java.util.List;

public interface UserService extends Service<UserResponseDto, UserRequestDto, Long> {

    UserResponseDto findByUsername(String username);

    List<UserResponseDto> paySalariesToCurrentCarServiceEmployees(Principal principal);

}
