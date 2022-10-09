package com.aston.carservice.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity<Long> {

    @Column(name = "price", nullable = false)
    private Long price;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private OrderStatusEntity orderStatus;
    @ManyToMany
    @JoinTable(name = "service_orders",
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<ServiceEntity> services = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "worker_id")
    private UserEntity worker;
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private UserEntity manager;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private UserEntity customer;

    public OrderEntity() {
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public OrderStatusEntity getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEntity orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<ServiceEntity> getServices() {
        return services;
    }

    public void setServices(List<ServiceEntity> services) {
        this.services = services;
    }

    public UserEntity getWorker() {
        return worker;
    }

    public void setWorker(UserEntity worker) {
        this.worker = worker;
    }

    public UserEntity getManager() {
        return manager;
    }

    public void setManager(UserEntity manager) {
        this.manager = manager;
    }

    public UserEntity getCustomer() {
        return customer;
    }

    public void setCustomer(UserEntity customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + getId() +
                ", price=" + price +
                ", orderStatus=" + orderStatus +
                '}';
    }

}
