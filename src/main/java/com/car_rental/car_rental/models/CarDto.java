package com.car_rental.car_rental.models;

import com.car_rental.car_rental.static_data.Status;

import jakarta.validation.constraints.*;

import lombok.Data;


@Data
public class CarDto {
    private Long id;

    @NotNull(message = "Brand is required")
    @NotBlank(message = "brand is required")
    private String brand;

    @NotNull(message = "model is required")
    @NotBlank(message = "model is required")
    private String model;

    @NotNull(message = "body type is required")
    @NotBlank(message = "body is required")
    private String body;

    @NotNull(message = "must put a year")
    @Min(value = 1900, message = "year must be >= 1900")
    @Max(value = 2100, message = "year must be <= 2100")
    private Integer year;

    @NotNull(message = "color us required")
    @NotBlank(message = "color is required")
    private String color;

    @NotNull(message = "mileage is required")
    @PositiveOrZero(message = "mileage must be >= 0")
    private Long mileage;

    @NotNull
    private Status status;

    @NotNull(message = "amount is required")
    @Positive(message = "amount must be > 0")
    private Double amount;

    @NotNull
    private Long branchId;

}