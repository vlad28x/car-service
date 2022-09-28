package com.example.carservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@Entity
@NoArgsConstructor
public class Users extends AbstractEntity<Long> {

    private String userName;
    private String password;
    private Integer salary;

    @OneToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "car_service_id", referencedColumnName = "id")
    private CarService carService;

}
