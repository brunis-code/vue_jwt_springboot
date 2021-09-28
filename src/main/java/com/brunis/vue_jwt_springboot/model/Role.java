package com.brunis.vue_jwt_springboot.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "sy_roles")
public class Role extends BaseEntity{

    private int weight;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Role() {
    }


    public Role(ERole name) {
        this.name = name;
    }

}