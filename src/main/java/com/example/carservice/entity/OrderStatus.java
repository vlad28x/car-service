package com.example.carservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class OrderStatus extends AbstractEntity<Long>{

    private String status;

    @OneToMany(mappedBy = "orderStatus")
    private List<Order> ordersList = new ArrayList<>();

}
