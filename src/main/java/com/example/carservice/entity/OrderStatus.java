package com.example.carservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
@NoArgsConstructor
public class OrderStatus extends AbstractEntity<Long>{

    private String status;
    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "status_id")
    private Orders orders;
}
