package com.aston.carservice.repository;

import com.aston.carservice.entity.ConsumableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumableRepository extends JpaRepository<ConsumableEntity, Long> {
}
