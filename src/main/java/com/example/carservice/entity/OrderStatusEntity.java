package com.example.carservice.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OrderStatusEntity extends BaseEntity<Long> {

    private String status;
    @OneToMany(mappedBy = "orderStatus")
    private List<OrderEntity> orders = new ArrayList<>();

    public OrderStatusEntity() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
                ", status='" + status + '\'' +
                '}';
    }

}
