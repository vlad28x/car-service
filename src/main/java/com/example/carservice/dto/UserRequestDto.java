package com.example.carservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequestDto {

    private Long id;
    private String username;
    private String password;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long salary;
    private Long roleId;
    private Long carServiceId;

}
