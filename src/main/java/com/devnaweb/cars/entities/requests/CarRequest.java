package com.devnaweb.cars.entities.requests;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CarRequest {

    @NotNull
    private String model;

    @NotNull
    private String brand;

    @NotNull
    private String color;
}
