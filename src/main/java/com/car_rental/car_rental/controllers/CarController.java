package com.car_rental.car_rental.controllers;

import com.car_rental.car_rental.entites.Car;
import com.car_rental.car_rental.models.CarDto;
import com.car_rental.car_rental.models.CarFilter;
import com.car_rental.car_rental.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody CarDto carDto) {
        Car createdCar = carService.create(carDto);
        return ResponseEntity.ok(createdCar);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Car car = carService.findById(id);
        return ResponseEntity.ok(car);
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> getAllCars() {
        List<CarDto> cars = carService.findAll();
        return ResponseEntity.ok(cars);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDto> updateCar(@PathVariable Long id, @RequestBody CarDto carDto) {
        carDto.setId(id);
        return ResponseEntity.ok(carService.update(carDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/filter")
    public ResponseEntity<List<Car>> filterCars(@RequestBody CarFilter filter) {
        List<Car> filteredCars = carService.filter(filter);
        return ResponseEntity.ok(filteredCars);
    }
}
