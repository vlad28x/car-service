package com.example.carservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Orders extends AbstractEntity<Long>{

    private Integer price;

    @OneToMany(mappedBy = "orders")
    private List<OrderStatus> statusList;
    
    @ManyToMany
    @JoinTable(name = "service_orders", joinColumns = @JoinColumn(name = "orders_id"),
    inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<Service> services;

    @OneToMany
    private List<Users> workers;

    @OneToMany
    private List<Users> managers;

    @OneToMany
    private List<Users> customers;

}
