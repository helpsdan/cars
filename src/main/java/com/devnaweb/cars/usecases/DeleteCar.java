package com.devnaweb.cars.usecases;

import com.devnaweb.cars.exceptions.CarNotFoundException;
import com.devnaweb.cars.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCar {

    @Autowired
    private CarRepository carRepository;

    public void execute(final long id) {
        carRepository.delete(carRepository.findById(id).orElseThrow(CarNotFoundException::new));
    }
}
