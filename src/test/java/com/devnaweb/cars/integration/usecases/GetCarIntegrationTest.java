package com.devnaweb.cars.integration.usecases;

import com.devnaweb.cars.entities.Car;
import com.devnaweb.cars.entities.responses.CarResponse;
import com.devnaweb.cars.exceptions.CarNotFoundException;
import com.devnaweb.cars.repositories.CarRepository;
import com.devnaweb.cars.usecases.GetCar;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GetCarIntegrationTest {

    @Autowired
    private GetCar getCar;

    @Autowired
    private CarRepository carRepository;

    @Test
    @DisplayName("Should get car when exists")
    void shouldGetCarWhenExists() {
        final CarResponse car = getCar.execute(1L);
        assertThat(car.getId(), is(1L));
        assertThat(car.getModel(), is("X1"));
        assertThat(car.getBrand(), is("BMW"));
        assertThat(car.getColor(), is("Black"));
    }

    @Test
    @DisplayName("Should fail get car when car not found")
    void shouldFailGetCarWhenCarNotFound() {
        assertThrows(CarNotFoundException.class, () -> {
            getCar.execute(100L);
        });
    }
}
