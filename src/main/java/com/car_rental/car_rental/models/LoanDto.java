package com.car_rental.car_rental.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class LoanDto {

    private Long id;

    @NotNull(message = "employeeId is required")
    private Long employeeId;

    @NotNull(message = "dateOfRental is required")
    @PastOrPresent(message = "dateOfRental must be today or in the past")
    private LocalDate dateOfRental;

    @NotNull(message = "reservationId is required")
    private Long reservationId;

    private String comment;

}
