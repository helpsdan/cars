package com.devnaweb.cars.fixtures;

import com.devnaweb.cars.entities.responses.CarResponse;

public class CarResponseFixture {

    public static CarResponse DEFAULT_VALUE = CarResponse.builder()
            .id(4L)
            .model("CIVIC")
            .brand("HONDA")
            .color("White")
            .build();
}
