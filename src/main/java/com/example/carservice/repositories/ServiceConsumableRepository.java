package com.example.carservice.repositories;

import com.example.carservice.entity.ServiceConsumable;
import com.example.carservice.entity.ServiceConsumableId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceConsumableRepository extends JpaRepository<ServiceConsumable, ServiceConsumableId> {
}
