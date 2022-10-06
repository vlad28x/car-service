package com.aston.carservice.util.mapper;

public interface Mapper<E, C, R> {

    E toEntity(C requestDto);

    E toEntity(C requestDto, E entity);

    R toResponseDto(E entity);

}
