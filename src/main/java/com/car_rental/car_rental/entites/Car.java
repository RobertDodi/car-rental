package com.car_rental.car_rental.entites;

import com.car_rental.car_rental.static_data.Status;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Data;

@Enabled
@Table(name = "cars")
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brand;
    private String model;
    private String body;
    private Long year;
    private String color;
    private Long mileage;
    private Status status;
    private Long amount;
    @OneToMany
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

}
