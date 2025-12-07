package com.car_rental.car_rental.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class LoanDto {

    private Long id;

    @NotNull(message = "reservationId is required")
    private Long reservationId;

    private String comment;

}
