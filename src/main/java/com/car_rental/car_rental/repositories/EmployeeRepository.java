package com.car_rental.car_rental.repositories;


import com.car_rental.car_rental.entites.Branch;
import com.car_rental.car_rental.entites.Employee;
import com.car_rental.car_rental.static_data.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    boolean existsByUsername(String username);
    Optional<Employee> findByUsername(String username);

    List<Employee> findByBranch_Id(Long branchId);

    List<Employee> findAllByBranch_IdAndPosition(Long branchId, Position position);
}
