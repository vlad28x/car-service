package com.example.carservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
@ToString(exclude = "carService", callSuper = true)
@EqualsAndHashCode(exclude = {"carService", "role"}, callSuper = true)
public class User extends AbstractEntity<Long> {

    private String username;
    private String password;
    private Long salary;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "car_service_id")
    private CarService carService;

}
