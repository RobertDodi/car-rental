package com.car_rental.car_rental.mappers;

import com.car_rental.car_rental.entites.Branch;
import com.car_rental.car_rental.entites.Car;
import com.car_rental.car_rental.models.CarDto;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    public CarDto toDto(Car car) {
        if (car == null) return null;
        CarDto dto = new CarDto();
        dto.setId(car.getId());
        dto.setBrand(car.getBrand());
        dto.setModel(car.getModel());
        dto.setBody(car.getBody());
        dto.setYear(car.getYear());
        dto.setColor(car.getColor());
        dto.setMileage(car.getMileage());
        dto.setStatus(car.getStatus());
        dto.setAmount(car.getAmount());
        dto.setBranchId(car.getBranch() != null ? car.getBranch().getId() : null);
        return dto;
    }

    public Car toEntity(CarDto dto) {
        if (dto == null) return null;
        Car car = new Car();
        car.setId(dto.getId());
        car.setBrand(dto.getBrand());
        car.setModel(dto.getModel());
        car.setBody(dto.getBody());
        car.setYear(dto.getYear());
        car.setColor(dto.getColor());
        car.setMileage(dto.getMileage());
        car.setStatus(dto.getStatus());
        car.setAmount(dto.getAmount());
        if (dto.getBranchId() != null) {
            Branch b = new Branch();
            b.setId(dto.getBranchId());
            car.setBranch(b);
        } else {
            car.setBranch(null);
        }
        return car;
    }
}