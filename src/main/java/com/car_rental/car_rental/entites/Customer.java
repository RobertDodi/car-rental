package com.car_rental.car_rental.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name ="customers")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    private String location;
    @OneToMany(mappedBy = "client")
    private List<Reservation> reservations;
    @JoinColumn(name = "rental_id")
    @ManyToOne
    private Rental rental;
}
