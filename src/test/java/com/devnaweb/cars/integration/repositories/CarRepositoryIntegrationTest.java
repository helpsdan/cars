package com.devnaweb.cars.integration.repositories;

import com.devnaweb.cars.entities.Car;
import com.devnaweb.cars.exceptions.CarNotFoundException;
import com.devnaweb.cars.fixtures.CarFixture;
import com.devnaweb.cars.repositories.CarRepository;
import java.util.List;
import java.util.Optional;
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
public class CarRepositoryIntegrationTest {

    @Autowired
    private CarRepository carRepository;

    private final Car car = CarFixture.DEFAULT_VALUE;

    @Test
    @DisplayName("Should create car with valid data")
    void shouldCreateCar() {
        final Car savedCar = carRepository.save(car);

        assertThat(car.getId(), is(savedCar.getId()));
        assertThat(car.getModel(), is(savedCar.getModel()));
        assertThat(car.getBrand(), is(savedCar.getBrand()));
        assertThat(car.getColor(), is(savedCar.getColor()));
    }

    @Test
    @DisplayName("Should delete car")
    void shouldDeleteCar() {
        carRepository.findById(4L).ifPresent(car -> carRepository.delete(car));

        final Optional<Car> carOptional = carRepository.findById(car.getId());

        assertThat(carOptional.isPresent(), is(false));
    }

    @Test
    @DisplayName("Should get car")
    void shouldGetCar() {
        carRepository.findById(1L).ifPresent(car -> {
            assertThat(car.getId(), is(1L));
            assertThat(car.getModel(), is("X1"));
            assertThat(car.getBrand(), is("BMW"));
            assertThat(car.getColor(), is("Black"));
        });
    }

    @Test
    @DisplayName("Should fail when car not found")
    void shouldFailGetCarWhenCarNotFound() {
        assertThrows(CarNotFoundException.class, () -> {
            carRepository.findById(100L).orElseThrow(CarNotFoundException::new);
        });
    }

    @Test
    @DisplayName("Should list cars")
    void shouldListCars() {
        final List<Car> cars = carRepository.findAll();

        assertThat(cars.size(), is(4));
        assertThat(cars.get(0).getId(), is(1L));
        assertThat(cars.get(0).getModel(), is("X1"));
        assertThat(cars.get(0).getBrand(), is("BMW"));
        assertThat(cars.get(0).getColor(), is("Black"));
    }

    @Test
    @DisplayName("Should update car")
    void shouldUpdateCar() {
        final Car savedCar = carRepository.findById(1L).orElseThrow(CarNotFoundException::new);

        assertThat(savedCar.getId(), is(1L));
        assertThat(savedCar.getModel(), is("X1"));
        assertThat(savedCar.getBrand(), is("BMW"));
        assertThat(savedCar.getColor(), is("Black"));

        final Car updatedCar = carRepository.save(savedCar.toBuilder().model("HR-V").brand("HONDA").color("White").build());

        assertThat(updatedCar.getId(), is(1L));
        assertThat(updatedCar.getModel(), is("HR-V"));
        assertThat(updatedCar.getBrand(), is("HONDA"));
        assertThat(updatedCar.getColor(), is("White"));
    }
}
