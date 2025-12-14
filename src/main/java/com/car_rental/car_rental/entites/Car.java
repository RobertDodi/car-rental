package com.car_rental.car_rental.entites;

import com.car_rental.car_rental.static_data.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;

@Entity
@Table(name = "cars")
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private String body;
    private Integer year;
    private String color;
    private Long mileage;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
    @OneToMany(mappedBy = "car")
    @JsonIgnore
    private List<Reservation> reservations;
}
