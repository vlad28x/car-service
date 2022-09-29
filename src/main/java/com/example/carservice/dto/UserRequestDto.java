package com.example.carservice.dto;

import lombok.Data;

@Data
public class UserRequestDto {

    private Long id;
    private String username;
    private String password;
    private Long salary;
    private Long roleId;
    private Long carServiceId;

}
