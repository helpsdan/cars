package com.devnaweb.cars.fixtures;

import com.devnaweb.cars.entities.requests.CarRequest;

public class CarRequestFixture {

    public static CarRequest DEFAULT_VALUE = CarRequest.builder()
            .model("CIVIC")
            .brand("HONDA")
            .color("White")
            .build();
}
