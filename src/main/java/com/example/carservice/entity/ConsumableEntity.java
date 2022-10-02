package com.example.carservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "consumable")
public class ConsumableEntity extends BaseEntity<Long> {

    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private Long price;
    @Column(name = "quantity")
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
