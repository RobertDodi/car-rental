package com.car_rental.car_rental.controller;

import com.car_rental.car_rental.Services.BranchService;
import com.car_rental.car_rental.entites.Branch;
import com.car_rental.car_rental.models.BranchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor

public class BranchController {
private BranchService branchService;

    @PostMapping
    public ResponseEntity<Branch> createBranch(@RequestBody BranchDto branch) {
        Branch createdBranch = branchService.createBranch(branch);
        return ResponseEntity.ok(createdBranch);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Branch> getBranchById(@PathVariable Long id) {
        Branch branch = branchService.findById(id);
        return ResponseEntity.ok(branch);
    }
    @GetMapping
    public ResponseEntity<List<Branch>> getAllBranches() {
        List<Branch> branches = branchService.getAllBranches();
        return ResponseEntity.ok(branches);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Branch> updateBranch(@PathVariable Long id, @RequestBody BranchDto branch) {
        Branch updatedBranch = branchService.updateBranch(id, branch);
        return ResponseEntity.ok(updatedBranch);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/city")
    public ResponseEntity<List<Branch>> findBranchesByCity(@RequestParam String city) {
        List<Branch> branches = branchService.findBranchesByCity(city);
        return ResponseEntity.ok(branches);
    }
    @GetMapping("/name")
    public ResponseEntity<List<Branch>> findBranchesByName(@RequestParam String name) {
        List<Branch> branches = branchService.findBranchesByName(name);
        return ResponseEntity.ok(branches);
    }

}
