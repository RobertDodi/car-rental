package com.car_rental.car_rental.entites;
import com.car_rental.car_rental.static_data.Position;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "employees")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String username;
    @Column( nullable = false)
    private String password;
    private Boolean active;
    @Enumerated(EnumType.STRING)
    private Position position;
    @JoinColumn(name = "branch_id")
    @ManyToOne
    private Branch branch;

}
