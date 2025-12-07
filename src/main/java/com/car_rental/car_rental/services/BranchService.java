package com.car_rental.car_rental.services;

import com.car_rental.car_rental.entites.Branch;
import com.car_rental.car_rental.entites.Rental;
import com.car_rental.car_rental.models.BranchDto;
import com.car_rental.car_rental.repositories.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private RentalService rentalService;

    public Branch findById(Long id) {
        return branchRepository.findById(id).orElseThrow(() -> new RuntimeException("Branch Not Found"));
    }

    public Branch updateBranch(Long id, BranchDto updatedBranch) {
        Branch branch = findById(id);
        branch.setName(updatedBranch.getName());
        branch.setCity(updatedBranch.getCity());
        Rental rental = rentalService.getRentalById(updatedBranch.getRentalId());
        branch.setRental(rental);
        return branchRepository.save(branch);
    }

    public void deleteBranch(Long id) {
        branchRepository.deleteById(id);
    }

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    public List<Branch> findBranchesByCity(String city) {
        return branchRepository.findByCityIgnoreCase(city);
    }
    public List<Branch>findBranchesByName(String name) {
        return branchRepository.findByName(name);
    }

    public Branch createBranch(BranchDto branchDto) {
        Branch branch= new Branch();
        branch.setName(branchDto.getName());
        branch.setCity(branchDto.getCity());
        branch.setRental(rentalService.getRentalById(branchDto.getRentalId()));
        return branchRepository.save(branch);
    }
}


