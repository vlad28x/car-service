package com.example.carservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequestDto {

    private Long id;
    private String username;
    private String password;
    private Long salary;
    private Long roleId;
    private Long carServiceId;

}
