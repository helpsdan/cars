package com.devnaweb.cars.integration.usecases;

import com.devnaweb.cars.repositories.CarRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CreateCarIntegrationTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    @DisplayName("")
    void shouldCreateCar() {

    }
}
