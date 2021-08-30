package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @Column(name = "service_rendered")
    private String serviceRendered;

    @Column(name = "address")
    private String address;
}
