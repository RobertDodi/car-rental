package com.car_rental.car_rental.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;

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
    @JsonIgnore
    private List<Employee> employees;
    @OneToMany(mappedBy = "branch")
    @JsonIgnore
    private List<Car> cars;
    @ManyToOne()
    @JoinColumn(name ="rental_id")
    private Rental rental;


}