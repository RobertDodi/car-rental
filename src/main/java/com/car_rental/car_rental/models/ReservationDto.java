package com.car_rental.car_rental.models;

import com.car_rental.car_rental.static_data.ReservationStatus;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class ReservationDto {
    private Long id;

    @NotNull(message = "dateOfBooking is required")
    @PastOrPresent(message = "dateOfBooking must be in the past or today")
    private LocalDate dateOfBooking;

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

    @NotNull(message = "amount is required")
    @Positive(message = "amount must be > 0")
    private Integer amount;

    @NotNull(message = "status is required")
    private ReservationStatus status;
}


