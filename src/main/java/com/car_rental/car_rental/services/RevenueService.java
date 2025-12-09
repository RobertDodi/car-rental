
package com.car_rental.car_rental.services;

import com.car_rental.car_rental.entites.Branch;
import com.car_rental.car_rental.entites.Rental;
import com.car_rental.car_rental.entites.Reservation;
import com.car_rental.car_rental.entites.Refund;
import com.car_rental.car_rental.repositories.RentalRepository;
import com.car_rental.car_rental.repositories.ReservationRepository;
import com.car_rental.car_rental.repositories.RefundRepository;
import com.car_rental.car_rental.static_data.ReservationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RevenueService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RefundRepository refundRepository;

    @Autowired
    private RentalRepository rentalRepository;


    public Double calculateBranchRevenue(Long branchId, LocalDate startDate, LocalDate endDate) {
        if (branchId == null) {
            throw new IllegalArgumentException("branchId is required");
        }
        if (startDate == null || endDate == null || endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Invalid date range");
        }

        double base = 0.0;
        List<Reservation> endedReservations = reservationRepository.findAllByStatus(ReservationStatus.ENDED);
        for (Reservation r : endedReservations) {
            if (r.getLoanBranch() == null || r.getLoanBranch().getId() == null) {
                continue;
            }
            if (!branchId.equals(r.getLoanBranch().getId())) {
                continue;
            }

            LocalDate dateTo = r.getDateTo();
            if (dateTo == null) {
                continue;
            }
            if (dateTo.isBefore(startDate)) {
                continue;
            }
            if (dateTo.isAfter(endDate)) {
                continue;
            }

            Double amount = r.getAmount();
            if (amount != null) {
                base += amount;
            }
        }

        double surcharges = 0.0;
        List<Refund> refundsForBranch = refundRepository.findByEmployee_Branch_Id(branchId);
        for (Refund f : refundsForBranch) {
            LocalDate ret = f.getDateOfReturn();
            if (ret == null) {
                continue;
            }

            if (ret.isBefore(startDate)) {
                continue;
            }
            if (ret.isAfter(endDate)) {
                continue;
            }

            Double s = f.getSurcharge();
            if (s != null) {
                surcharges += s;
            }
        }

        return base + surcharges;
    }


    public Double calculateRentalRevenue(Long rentalId, LocalDate startDate, LocalDate endDate) {
        if (rentalId == null) {
            throw new IllegalArgumentException("rentalId is required");
        }
        if (startDate == null || endDate == null || endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Invalid date range");
        }

        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        double total = 0.0;

        List<Branch> branches = rental.getBranches();
        if (branches != null) {
            for (Branch b : branches) {
                if (b == null || b.getId() == null) {
                    continue;
                }
                Double branchRevenue = calculateBranchRevenue(b.getId(), startDate, endDate);
                if (branchRevenue != null) {
                    total += branchRevenue;
                }
            }
        }

        return total;
    }
}
