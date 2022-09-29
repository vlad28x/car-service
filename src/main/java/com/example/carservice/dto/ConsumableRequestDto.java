package com.example.carservice.dto;

import lombok.Data;

@Data
public class ConsumableRequestDto {

    private Long id;
    private String name;
    private Long price;
    private Long quantity;
}
