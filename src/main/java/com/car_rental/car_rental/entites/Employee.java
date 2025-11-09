package com.car_rental.car_rental.entites;

import com.car_rental.car_rental.static_data.Position;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "employees")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Position position;
    //private Branch branch;
}
