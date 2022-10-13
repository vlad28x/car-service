package com.aston.carservice.repository;

import com.aston.carservice.entity.CarServiceEntity;
import com.aston.carservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findAllByCarServiceAndRoleNameIn(CarServiceEntity carServiceEntity, List<String> roles);

}
