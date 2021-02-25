package com.devnaweb.cars.usecases;

import com.devnaweb.cars.entities.Car;
import com.devnaweb.cars.entities.converters.CarsConverter;
import com.devnaweb.cars.entities.responses.CarResponse;
import com.devnaweb.cars.exceptions.CarNotFoundException;
import com.devnaweb.cars.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCar {

    @Autowired
    private CarRepository carRepository;

    public CarResponse execute(final long id, final Car car) {
        final Car carSaved = carRepository.findById(id).orElseThrow(CarNotFoundException::new);
        return CarsConverter.toResponse(carRepository.save(carSaved.toBuilder()
                .id(carSaved.getId())
                .model(car.getModel())
                .brand(car.getBrand())
                .color(car.getColor())
                .build()));
    }
}
