package com.car_rental.car_rental.Services;

import com.car_rental.car_rental.entites.Branch;
import com.car_rental.car_rental.repositories.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchService {
    @Autowired
    private BranchRepository branchRepository;

    public Branch findById(Long id) {
        return branchRepository.findById(id).orElseThrow(()-> new RuntimeException("Branch Not Found"));
    }
}
