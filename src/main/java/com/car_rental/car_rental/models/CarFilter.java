package com.car_rental.car_rental.models;

import com.car_rental.car_rental.static_data.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarFilter {
    private String brand;
    private String model;
    private String body;
    private Integer year;
    private String color;
    private Long mileage;
    private String status;
    private Double amount;
}
