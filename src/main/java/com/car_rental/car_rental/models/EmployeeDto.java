package com.car_rental.car_rental.models;

import com.car_rental.car_rental.static_data.Position;
import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Position position;
    private Long branchId;
}
