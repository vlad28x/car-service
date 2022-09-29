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
@Table(name = "orders")
@ToString(exclude = {"services", "worker", "manager", "customer"}, callSuper = true)
@EqualsAndHashCode(exclude = {"services", "worker", "manager", "customer"}, callSuper = true)
public class Order extends AbstractEntity<Long> {

    private Long price;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private OrderStatus orderStatus;
    @ManyToMany
    @JoinTable(name = "service_orders", joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<Service> services = new ArrayList<>();
    @ManyToOne
    private User worker;
    @ManyToOne
    private User manager;
    @ManyToOne
    private User customer;

}
