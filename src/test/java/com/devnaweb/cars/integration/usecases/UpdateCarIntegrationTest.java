package com.devnaweb.cars.integration.usecases;

import com.devnaweb.cars.entities.Car;
import com.devnaweb.cars.entities.responses.CarResponse;
import com.devnaweb.cars.exceptions.CarNotFoundException;
import com.devnaweb.cars.fixtures.CarFixture;
import com.devnaweb.cars.repositories.CarRepository;
import com.devnaweb.cars.usecases.GetCar;
import com.devnaweb.cars.usecases.UpdateCar;
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
public class UpdateCarIntegrationTest {

    @Autowired
    private UpdateCar updateCar;

    @Autowired
    private GetCar getCar;

    @Autowired
    private CarRepository carRepository;

    @Test
    @DisplayName("Should update car when exists")
    void shouldUpdateCarWhenExists() {
        final CarResponse car = getCar.execute(4L);
        assertThat(car.getId(), is(4L));
        assertThat(car.getModel(), is("CIVIC"));
        assertThat(car.getBrand(), is("HONDA"));
        assertThat(car.getColor(), is("White"));

        final CarResponse carUpdated = updateCar.execute(4L, Car.builder().model("Fox").brand("VW").color("Black").build());
        assertThat(carUpdated.getId(), is(4L));
        assertThat(carUpdated.getModel(), is("Fox"));
        assertThat(carUpdated.getBrand(), is("VW"));
        assertThat(carUpdated.getColor(), is("Black"));
    }

    @Test
    @DisplayName("Should fail update car when car not found")
    void shouldFailUpdateCarWhenCarNotFound() {
        assertThrows(CarNotFoundException.class, () -> {
            updateCar.execute(100L, CarFixture.DEFAULT_VALUE);
        });
    }
}
