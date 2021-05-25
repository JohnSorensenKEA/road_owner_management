package com.example.road_owner_management.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role {

    @Id
    private Integer id;

    private String name;
}
