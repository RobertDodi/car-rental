package com.car_rental.car_rental.entites;

import com.car_rental.car_rental.static_data.Status;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Data;

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
    private Status status;
    private Long amount;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
    @OneToMany(mappedBy = "car")
    private List<Reservation> reservations;
}
