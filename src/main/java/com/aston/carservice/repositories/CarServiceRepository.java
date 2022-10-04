package com.aston.carservice.repositories;

import com.aston.carservice.entity.CarServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarServiceRepository extends JpaRepository<CarServiceEntity, Long> {
}
