package com.car_rental.car_rental.Services;
import com.car_rental.car_rental.entites.Car;
import com.car_rental.car_rental.models.CarDto;
import com.car_rental.car_rental.repositories.CarRepository;
import com.car_rental.car_rental.repositories.ReservationRepository;
import com.car_rental.car_rental.static_data.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;



}
