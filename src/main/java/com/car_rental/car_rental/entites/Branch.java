package com.car_rental.car_rental.entites;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "branch")
@Data
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
