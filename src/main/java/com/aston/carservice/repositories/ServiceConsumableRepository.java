package com.aston.carservice.repositories;

import com.aston.carservice.entity.ServiceConsumableId;
import com.aston.carservice.entity.ServiceConsumableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceConsumableRepository extends JpaRepository<ServiceConsumableEntity, ServiceConsumableId> {
}