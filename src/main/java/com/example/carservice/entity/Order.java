package com.example.carservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order extends AbstractEntity<Long>{

    private Long price;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private OrderStatus orderStatus;
    
    @ManyToMany
    @JoinTable(name = "service_orders", joinColumns = @JoinColumn(name = "orders_id"),
    inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<Service> services = new ArrayList<>();

    @OneToMany
    private List<User> workers = new ArrayList<>();

    @OneToMany
    private List<User> managers = new ArrayList<>();

    @OneToMany
    private List<User> customers = new ArrayList<>();

}
