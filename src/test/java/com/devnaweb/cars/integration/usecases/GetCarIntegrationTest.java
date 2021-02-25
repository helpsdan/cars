package com.devnaweb.cars.integration.usecases;

import com.devnaweb.cars.entities.Car;
import com.devnaweb.cars.entities.responses.CarResponse;
import com.devnaweb.cars.exceptions.CarNotFoundException;
import com.devnaweb.cars.fixtures.CarFixture;
import com.devnaweb.cars.repositories.CarRepository;
import com.devnaweb.cars.usecases.CreateCar;
import com.devnaweb.cars.usecases.GetCar;
import org.junit.jupiter.api.BeforeEach;
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

    @Autowired
    private CreateCar createCar;

    private final Car car = CarFixture.DEFAULT_VALUE;

    @Test
    @DisplayName("Should get car when exists")
    void shouldGetCarWhenExists() {
        createCar.execute(car);

        final CarResponse car = getCar.execute(4L);
        assertThat(car.getId(), is(4L));
        assertThat(car.getModel(), is("CIVIC"));
        assertThat(car.getBrand(), is("HONDA"));
        assertThat(car.getColor(), is("White"));
    }

    @Test
    @DisplayName("Should fail get car when car not found")
    void shouldFailGetCarWhenCarNotFound() {
        assertThrows(CarNotFoundException.class, () -> {
            getCar.execute(100L);
        });
    }
}
