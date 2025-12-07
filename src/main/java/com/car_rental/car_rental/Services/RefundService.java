package com.car_rental.car_rental.services;
import com.car_rental.car_rental.repositories.RefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefundService {
    @Autowired
    private RefundRepository refundRepository;
}
