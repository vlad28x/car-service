package com.example.carservice.repositories;

import com.example.carservice.entity.Consumable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumableRepository extends JpaRepository<Consumable, Long> {
}
