package com.example.carservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Entity
@NoArgsConstructor
public class Role extends AbstractEntity<Long> {

    private String name;

    @OneToOne(mappedBy = "role")
    private Users users;

}
