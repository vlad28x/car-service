package com.example.carservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude = {"serviceConsumables", "orders", "carService"}, callSuper = true)
@EqualsAndHashCode(exclude = {"serviceConsumables", "orders", "carService"}, callSuper = true)
public class Service extends AbstractEntity<Long> {

    private String name;
    private Long price;
    @ManyToOne
    @JoinColumn(name = "car_service_id")
    private CarService carService;
    @OneToMany(mappedBy = "service")
    private List<ServiceConsumable> serviceConsumables = new ArrayList<>();
    @ManyToMany(mappedBy = "services")
    private List<Order> orders = new ArrayList<>();

}
