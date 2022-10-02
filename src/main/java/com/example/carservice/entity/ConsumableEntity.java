package com.example.carservice.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ConsumableEntity extends BaseEntity<Long> {

    private String name;
    private Long price;
    private Long quantity;
    @OneToMany(mappedBy = "consumable")
    private List<ServiceConsumableEntity> serviceConsumables = new ArrayList<>();

    public ConsumableEntity() {
    }

    public ConsumableEntity(Long id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public List<ServiceConsumableEntity> getServiceConsumables() {
        return serviceConsumables;
    }

    public void setServiceConsumables(List<ServiceConsumableEntity> serviceConsumables) {
        this.serviceConsumables = serviceConsumables;
    }

    @Override
    public String toString() {
        return "ConsumableEntity{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

}
