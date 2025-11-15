package com.car_rental.car_rental.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "branch")
@Data
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    @OneToMany(mappedBy = "branch")
    private List<Employee> employees;
    @OneToMany(mappedBy = "branch")
    private List<Car> cars;
    @ManyToOne()
    @JoinColumn(name ="rental_id")
    private Rental rental;


}