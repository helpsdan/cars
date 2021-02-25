package com.devnaweb.cars.integration.usecases;

import com.devnaweb.cars.entities.Car;
import com.devnaweb.cars.entities.responses.CarResponse;
import com.devnaweb.cars.exceptions.CarNotFoundException;
import com.devnaweb.cars.fixtures.CarFixture;
import com.devnaweb.cars.repositories.CarRepository;
import com.devnaweb.cars.usecases.CreateCar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CreateCarIntegrationTest {

    @Autowired
    private CreateCar createCar;

    @Autowired
    private CarRepository carRepository;

    private final Car car = CarFixture.DEFAULT_VALUE;

    @BeforeEach
    void setUp() {
        carRepository.deleteAll();
    }

    @Test
    @DisplayName("Should create car")
    void shouldCreateCar() {
        final CarResponse carResponse = createCar.execute(car);

        assertThat(carResponse.getId(), is(car.getId()));
        assertThat(carResponse.getModel(), is(car.getModel()));
        assertThat(carResponse.getBrand(), is(car.getBrand()));
        assertThat(carResponse.getColor(), is(car.getColor()));

        final Car carSaved = carRepository.findById(car.getId()).orElseThrow(CarNotFoundException::new);

        assertThat(carSaved.getId(), is(car.getId()));
        assertThat(carSaved.getModel(), is(car.getModel()));
        assertThat(carSaved.getBrand(), is(car.getBrand()));
        assertThat(carSaved.getColor(), is(car.getColor()));
    }
}
