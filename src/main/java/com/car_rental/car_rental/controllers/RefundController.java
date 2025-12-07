package com.car_rental.car_rental.controllers;

import com.car_rental.car_rental.services.RefundService;
import com.car_rental.car_rental.entites.Refund;
import com.car_rental.car_rental.models.RefundDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/refunds")
public class RefundController {

    private final RefundService refundService;

    @Autowired
    public RefundController(RefundService refundService) {
        this.refundService = refundService;
    }

    @PostMapping
    public ResponseEntity<Refund> createRefund(@RequestBody RefundDto refundDto) {
        Refund createdRefund = refundService.createRefund(refundDto);
        return ResponseEntity.ok(createdRefund);
    }

    @GetMapping
    public ResponseEntity<List<Refund>> getAllRefunds() {
        List<Refund> refunds = refundService.findAll();
        return ResponseEntity.ok(refunds);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Refund> getRefundById(@PathVariable Long id) {
        return ResponseEntity.ok(refundService.findById(id));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<Refund>> getRefundsByBranch(@PathVariable Long branchId) {
        List<Refund> refunds = refundService.findByBranch(branchId);
        return ResponseEntity.ok(refunds);
    }
}
