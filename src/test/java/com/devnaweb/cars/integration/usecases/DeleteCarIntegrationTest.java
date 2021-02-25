package com.devnaweb.cars.integration.usecases;

import com.devnaweb.cars.entities.Car;
import com.devnaweb.cars.entities.responses.CarResponse;
import com.devnaweb.cars.exceptions.CarNotFoundException;
import com.devnaweb.cars.fixtures.CarFixture;
import com.devnaweb.cars.repositories.CarRepository;
import com.devnaweb.cars.usecases.CreateCar;
import com.devnaweb.cars.usecases.DeleteCar;
import java.util.List;
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
public class DeleteCarIntegrationTest {

    @Autowired
    private DeleteCar deleteCar;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CreateCar createCar;

    private final Car car = CarFixture.DEFAULT_VALUE;

    @BeforeEach
    void setUp() {
        carRepository.deleteAll();
    }

    @Test
    @DisplayName("Should delete car when exists")
    void shouldDeleteCarWhenExists() {
        final CarResponse carResponse = createCar.execute(car);

        assertThat(carResponse.getId(), is(car.getId()));
        assertThat(carResponse.getModel(), is(car.getModel()));
        assertThat(carResponse.getBrand(), is(car.getBrand()));
        assertThat(carResponse.getColor(), is(car.getColor()));

        deleteCar.execute(4L);

        final List<Car> carList = carRepository.findAll();
        assertThat(carList.size(), is(0));
    }

    @Test
    @DisplayName("Should fail delete car when car not found")
    void shouldFailDeleteCarWhenCarNotFound() {
        assertThrows(CarNotFoundException.class, () -> {
           deleteCar.execute(100L);
        });
    }
}
