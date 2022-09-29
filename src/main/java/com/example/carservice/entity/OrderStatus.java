package com.example.carservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude = "orders", callSuper = true)
@EqualsAndHashCode(exclude = "orders", callSuper = true)
public class OrderStatus extends AbstractEntity<Long> {

    private String status;
    @OneToMany(mappedBy = "orderStatus")
    private List<Order> orders = new ArrayList<>();

}
