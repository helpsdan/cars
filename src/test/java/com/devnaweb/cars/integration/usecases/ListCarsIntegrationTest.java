package com.devnaweb.cars.integration.usecases;

import com.devnaweb.cars.entities.Car;
import com.devnaweb.cars.entities.responses.CarResponse;
import com.devnaweb.cars.fixtures.CarFixture;
import com.devnaweb.cars.repositories.CarRepository;
import com.devnaweb.cars.usecases.CreateCar;
import com.devnaweb.cars.usecases.ListCars;
import java.util.List;
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
public class ListCarsIntegrationTest {

    @Autowired
    private ListCars listCars;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CreateCar createCar;

    private final Car car = CarFixture.DEFAULT_VALUE;

    @Test
    @DisplayName("Should list cars when exist")
    void shouldListCarsWhenExist() {
        createCar.execute(car);
        carRepository.deleteAll();

        final List<CarResponse> cars = listCars.execute();

        assertThat(cars.size(), is(1));
    }
}
