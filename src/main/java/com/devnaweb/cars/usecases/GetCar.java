package com.devnaweb.cars.usecases;

import com.devnaweb.cars.entities.converters.CarsConverter;
import com.devnaweb.cars.entities.responses.CarResponse;
import com.devnaweb.cars.exceptions.CarNotFoundException;
import com.devnaweb.cars.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetCar {

    @Autowired
    private CarRepository carRepository;

    public CarResponse execute(final long id) {
        return CarsConverter.toResponse(carRepository.findById(id).orElseThrow(CarNotFoundException::new));
    }
}
