package com.car_rental.car_rental.models;

import com.car_rental.car_rental.entites.Rental;
import com.car_rental.car_rental.entites.Reservation;
import org.apache.catalina.LifecycleState;

import java.util.List;

public class CustomerDto{
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String location;
    private List<Reservation> reservations;
    private Rental rental;
}
