package com.devnaweb.cars.entities.converters;

import com.devnaweb.cars.entities.Car;
import com.devnaweb.cars.entities.requests.CarRequest;
import com.devnaweb.cars.entities.responses.CarResponse;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.util.Assert;

public class CarsConverter {

    public static List<CarResponse> toResponseList(final List<Car> cars) {
        return cars.stream().map(car ->
                CarResponse.builder()
                        .id(car.getId())
                        .model(car.getModel())
                        .brand(car.getBrand())
                        .color(car.getColor())
                        .build())
                .collect(Collectors.toList());
    }

    public static Car toEntity(final CarRequest carRequest) {
        Assert.notNull(carRequest, "Car request cannot be null");
        Assert.notNull(carRequest.getModel(), "Model cannot be null");
        Assert.notNull(carRequest.getBrand(), "Brand cannot be null");
        Assert.notNull(carRequest.getColor(), "Color cannot be null");
        return com.devnaweb.cars.entities.Car.builder()
                .id(new Random().nextLong())
                .model(carRequest.getModel())
                .brand(carRequest.getBrand())
                .color(carRequest.getColor())
                .build();
    }

    public static CarResponse toResponse(final Car car) {
        Assert.notNull(car, "Car cannot be null");
        Assert.notNull(car.getModel(), "Car model cannot be null");
        Assert.notNull(car.getBrand(), "Car brand cannot be null");
        Assert.notNull(car.getColor(), "Car color cannot be null");
        return CarResponse.builder()
                .id(car.getId())
                .model(car.getModel())
                .brand(car.getBrand())
                .color(car.getColor())
                .build();
    }
}
