package com.car_rental.car_rental.Services;

import com.car_rental.car_rental.entites.Car;
import com.car_rental.car_rental.models.CarDto;
import com.car_rental.car_rental.repositories.CarRepository;
import com.car_rental.car_rental.mappers.CarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public Car create(CarDto carDto) {
        if (carDto.getId() == null) {
            Car car = carMapper.toEntity(carDto);
            return carRepository.save(car);
        } else {
            throw new IllegalArgumentException("ID must be null when creating a new car");
        }
    }

    public CarDto findById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Car not found with id: " + id));
        return carMapper.toDto(car);
    }

    public List<CarDto> findAll() {
        return carRepository.findAll()
                .stream()
                .map(carMapper::toDto)
                .toList();
    }

    public void delete(Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Car not found with id: " + id);
        }
    }

    public CarDto update(CarDto carDto) {
        if (carDto.getId() != null) {
            Car car = carRepository.findById(carDto.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Car not found with id: " + carDto.getId()));
            Car updatedCar = carMapper.toEntity(carDto);
            return carMapper.toDto(carRepository.save(updatedCar));
        } else {
            throw new IllegalArgumentException("ID must not be null when updating a car");
        }

    }
}