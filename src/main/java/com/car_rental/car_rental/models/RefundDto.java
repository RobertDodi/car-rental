package com.car_rental.car_rental.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class RefundDto {

    private Long id;

    @NotNull(message = "employeeId is required")
    private Long employeeId;

    @NotNull(message = "dateOfReturn is required")
    private LocalDate dateOfReturn;

    @NotNull(message = "reservationId is required")
    private Long reservationId;

    @NotNull(message = "surcharge is required")
    private Long surcharge;

    private String comment;

}
