package com.example.carservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarServiceRequestDto {

    private Long id;
    private Long budget;
    private String name;

}
