package com.car_rental.car_rental.entites;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="customers")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String location;
}
