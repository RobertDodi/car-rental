package com.car_rental.car_rental.Services;
import com.car_rental.car_rental.entites.Car;
import com.car_rental.car_rental.entites.Employee;
import com.car_rental.car_rental.entites.Refund;
import com.car_rental.car_rental.entites.Reservation;
import com.car_rental.car_rental.models.RefundDto;
import com.car_rental.car_rental.repositories.CarRepository;
import com.car_rental.car_rental.repositories.RefundRepository;
import com.car_rental.car_rental.static_data.ReservationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
public class RefundService {
    @Autowired
    private RefundRepository refundRepository;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CarRepository carRepository;

    public Refund createRefund(RefundDto refundDto) {
        Refund refund = new Refund();
        Reservation reservation = reservationService.findById(refundDto.getReservationId());
        Employee employee = employeeService.getLoggedInEmployee();
        refund.setReservation(reservationService.findById(refundDto.getReservationId()));
        refund.setEmployee(employee);
        refund.setComment(refundDto.getComment());
        refund.setDateOfReturn(LocalDate.now());
        long diffDates = ChronoUnit.DAYS.between(reservation.getDateTo(), refund.getDateOfReturn());
        refund.setSurcharge(reservation.getCar().getAmount() * diffDates);
        reservation.setStatus(ReservationStatus.ENDED);
        reservationService.flush(reservation);
        Car car = reservation.getCar();
        car.setBranch(reservation.getReturnBranch());
        carRepository.save(car);
        return refundRepository.save(refund);
    }
    public List<Refund> findAll(){
        return refundRepository.findAll();
    }
    public Refund findById(Long id){
        return refundRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Refund not found"));
    }

    public List<Refund> findByBranch(Long id){
        return refundRepository.findByEmployee_Branch_Id(id);
    }
}
