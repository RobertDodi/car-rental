package com.car_rental.car_rental.models;

import lombok.Data;

@Data
public class BranchDto {
    private String name;
    private String city;
    private Long rentalId;
}