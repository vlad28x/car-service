package com.aston.carservice.repository;

import com.aston.carservice.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByCustomerUsername(String username);

    List<OrderEntity> findAllByWorkerUsername(String username);

}
