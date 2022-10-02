package com.example.carservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_status")
public class OrderStatusEntity extends BaseEntity<Long> {

    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "orderStatus")
    private List<OrderEntity> orders = new ArrayList<>();

    public OrderStatusEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "OrderStatusEntity{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                '}';
    }

}
