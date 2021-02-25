package com.devnaweb.cars.entities.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class CarResponse {

    private long id;
    private String model;
    private String brand;
    private String color;
}
