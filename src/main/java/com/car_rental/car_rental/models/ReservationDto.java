package com.car_rental.car_rental.models;

import com.car_rental.car_rental.static_data.ReservationStatus;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationDto {
    private Long id;

    @NotNull(message = "clientId is required")
    private Long clientId;

    @NotNull(message = "carId is required")
    private Long carId;

    @NotNull(message = "dateFrom is required")
    private LocalDate dateFrom;

    @NotNull(message = "dateTo is required")
    private LocalDate dateTo;

    @NotNull(message = "returnBranchId is required")
    private Long returnBranchId;

    @NotNull(message = "loanBranchId is required")
    private Long loanBranchId;

    private ReservationStatus status;
}


