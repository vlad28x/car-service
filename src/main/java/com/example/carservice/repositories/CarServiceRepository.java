package com.example.carservice.repositories;

import com.example.carservice.entity.CarService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarServiceRepository extends JpaRepository<CarService, Long> {
}
