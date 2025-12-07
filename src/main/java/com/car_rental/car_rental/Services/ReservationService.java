package com.car_rental.car_rental.Services;
import com.car_rental.car_rental.entites.Branch;
import com.car_rental.car_rental.entites.Car;
import com.car_rental.car_rental.entites.Customer;
import com.car_rental.car_rental.entites.Reservation;
import com.car_rental.car_rental.models.ReservationDto;
import com.car_rental.car_rental.repositories.ReservationRepository;
import com.car_rental.car_rental.static_data.ReservationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CarService carService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BranchService branchService;

    public Reservation create(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        return save(reservationDto, reservation);
    }

    public Reservation update(ReservationDto reservationDto) {
        Reservation reservation = reservationRepository.findById(reservationDto.getId())
                .orElseThrow(()-> new RuntimeException("Reservation not found"));
        return save(reservationDto, reservation);
    }

    /**
     * Checks if a car is available for booking during the specified date range
     * @param carId The ID of the car to check
     * @param startDate Start date of the requested booking
     * @param endDate End date of the requested booking
     * @param excludeReservationId Optional reservation ID to exclude (for updates)
     * @return true if the car is available, false otherwise
     */
    public boolean isCarAvailable(Long carId, LocalDate startDate, LocalDate endDate, Long excludeReservationId) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be before or equal to end date");
        }
        
        List<Reservation> overlapping = reservationRepository.findOverlappingReservations(carId, startDate, endDate);
        
        // If we're updating an existing reservation, exclude it from the check
        if (excludeReservationId != null) {
            overlapping.removeIf(r -> r.getId().equals(excludeReservationId));
        }
        
        return overlapping.isEmpty();
    }
    
    private void validateReservationDates(ReservationDto reservationDto, Long excludeReservationId) {
        if (reservationDto.getDateFrom().isAfter(reservationDto.getDateTo())) {
            throw new IllegalArgumentException("End date must be after start date");
        }
        
        if (!isCarAvailable(reservationDto.getCarId(), reservationDto.getDateFrom(), 
                          reservationDto.getDateTo(), excludeReservationId)) {
            throw new IllegalStateException("Car is already booked for the selected dates");
        }
    }
    
    private Reservation save(ReservationDto reservationDto, Reservation reservation) {
        // First validate the reservation dates
        validateReservationDates(reservationDto, reservation.getId());
        
        Car car = carService.findById(reservationDto.getCarId());
        Branch loanBranch = branchService.findById(reservationDto.getLoanBranchId());
        Branch returnBranch = branchService.findById(reservationDto.getReturnBranchId());
        Customer customer = customerService.findById(reservationDto.getClientId());
        
        reservation.setCar(car);
        reservation.setLoanBranch(loanBranch);
        reservation.setReturnBranch(returnBranch);
        reservation.setClient(customer);
        reservation.setDateOfBooking(LocalDate.now());
        reservation.setStatus(ReservationStatus.BOOKED);
        reservation.setDateFrom(reservationDto.getDateFrom());
        reservation.setDateTo(reservationDto.getDateTo());
        
        long duration = ChronoUnit.DAYS.between(reservation.getDateFrom(), reservation.getDateTo());
        if (duration < 1) {
            duration = 1; // Minimum 1 day rental
        }
        reservation.setAmount(duration * car.getAmount());
        
        return reservationRepository.save(reservation);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Reservation findById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    public Reservation cancelReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservation.setStatus(ReservationStatus.CANCELLED);
        return reservationRepository.save(reservation);
    }

    public List<Reservation> findAllByClientEmail(String clientEmail) {
        return reservationRepository.findAllByClient_Email(clientEmail);
    }

    public void flush(Reservation reservation) {
        reservationRepository.save(reservation);
    }
}
