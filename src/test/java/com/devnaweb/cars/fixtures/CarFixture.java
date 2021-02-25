package com.devnaweb.cars.fixtures;

import com.devnaweb.cars.entities.Car;
import java.util.Random;

public class CarFixture {

    public static Car DEFAULT_VALUE = Car.builder()
            .id(4L)
            .model("CIVIC")
            .brand("HONDA")
            .color("White")
            .build();
}
