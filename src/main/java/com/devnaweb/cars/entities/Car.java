package com.devnaweb.cars.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "cars")
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Car {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "model", nullable = false)
    @NotNull(message="Model cannot be null")
    private String model;

    @Column(name = "brand", nullable = false)
    @NotNull(message="Brand cannot be null")
    private String brand;

    @Column(name = "color", nullable = false)
    @NotNull(message="Color cannot be null")
    private String color;
}
