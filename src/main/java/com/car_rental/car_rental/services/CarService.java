package com.car_rental.car_rental.services;

import com.car_rental.car_rental.entites.Branch;
import com.car_rental.car_rental.entites.Car;
import com.car_rental.car_rental.models.CarDto;
import com.car_rental.car_rental.models.CarFilter;
import com.car_rental.car_rental.repositories.CarRepository;
import com.car_rental.car_rental.mappers.CarMapper;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final BranchService branchService;

    public Car create(CarDto carDto) {
        if (carDto.getId() == null) {
            Car car = carMapper.toEntity(carDto);
            Branch branch = branchService.findById(carDto.getBranchId());
            car.setBranch(branch);
            return carRepository.save(car);
        } else {
            throw new IllegalArgumentException("ID must be null when creating a new car");
        }
    }

    public Car findById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Car not found with id: " + id));
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
        if (carDto.getId() != null && carRepository.existsById(carDto.getId())) {
            Car updatedCar = carMapper.toEntity(carDto);
            Branch branch = branchService.findById(carDto.getBranchId());
            updatedCar.setBranch(branch);
            return carMapper.toDto(carRepository.save(updatedCar));
        } else {
            throw new IllegalArgumentException("ID must not be null when updating a car");
        }
    }

    public List<Car> filter(CarFilter carFilter) {
        Specification<Car> carSpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (carFilter.getBrand() != null) {
                predicates.add(criteriaBuilder.equal(root.get("brand"), carFilter.getBrand()));
            }
            if (carFilter.getModel() != null) {
                predicates.add(criteriaBuilder.equal(root.get("model"), carFilter.getModel()));
            }
            if (carFilter.getBody() != null) {
                predicates.add(criteriaBuilder.equal(root.get("body"), carFilter.getBody()));
            }
            if (carFilter.getYear() != null) {
                predicates.add(criteriaBuilder.equal(root.get("year"), carFilter.getYear()));
            }
            if (carFilter.getColor() != null) {
                predicates.add(criteriaBuilder.equal(root.get("color"), carFilter.getColor()));
            }
            if (carFilter.getMileage() != null) {
                predicates.add(criteriaBuilder.equal(root.get("mileage"), carFilter.getMileage()));
            }
            if (carFilter.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), carFilter.getStatus()));
            }
            if (carFilter.getAmount() != null) {
                predicates.add(criteriaBuilder.equal(root.get("amount"), carFilter.getAmount()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        return carRepository.findAll(carSpecification);
    }
}