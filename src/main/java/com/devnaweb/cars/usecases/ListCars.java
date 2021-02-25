package com.devnaweb.cars.usecases;

import com.devnaweb.cars.entities.converters.CarsConverter;
import com.devnaweb.cars.entities.responses.CarResponse;
import com.devnaweb.cars.repositories.CarRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListCars {

    @Autowired
    private CarRepository carRepository;

    public List<CarResponse> execute() {
        return CarsConverter.toResponseList(carRepository.findAll());
    }
}
