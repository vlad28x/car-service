package com.example.carservice.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ServiceEntity extends BaseEntity<Long> {

    private String name;
    private Long price;
    @ManyToOne
    @JoinColumn(name = "car_service_id")
    private CarServiceEntity carService;
    @OneToMany(mappedBy = "service")
    private List<ServiceConsumableEntity> serviceConsumables = new ArrayList<>();
    @ManyToMany(mappedBy = "services")
    private List<OrderEntity> orders = new ArrayList<>();

    public ServiceEntity() {
    }

    public ServiceEntity(Long id){
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

    public CarServiceEntity getCarService() {
        return carService;
    }

    public void setCarService(CarServiceEntity carService) {
        this.carService = carService;
    }

    public List<ServiceConsumableEntity> getServiceConsumables() {
        return serviceConsumables;
    }

    public void setServiceConsumables(List<ServiceConsumableEntity> serviceConsumables) {
        this.serviceConsumables = serviceConsumables;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "ServiceEntity{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

}
