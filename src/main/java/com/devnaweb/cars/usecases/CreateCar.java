package com.devnaweb.cars.usecases;

import com.devnaweb.cars.entities.Car;
import com.devnaweb.cars.entities.converters.CarsConverter;
import com.devnaweb.cars.entities.responses.CarResponse;
import com.devnaweb.cars.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCar {

    @Autowired
    private CarRepository carRepository;

    public CarResponse execute(final Car car) {
        return CarsConverter.toResponse(carRepository.save(car));
    }
}
