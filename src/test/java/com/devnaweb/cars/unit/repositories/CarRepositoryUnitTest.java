package com.devnaweb.cars.unit.repositories;

import com.devnaweb.cars.entities.Car;
import com.devnaweb.cars.exceptions.CarNotFoundException;
import com.devnaweb.cars.fixtures.CarFixture;
import com.devnaweb.cars.repositories.CarRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CarRepositoryUnitTest {

    @MockBean
    private CarRepository carRepository;

    private final Car car = CarFixture.DEFAULT_VALUE;

    @Test
    @DisplayName("Should create car")
    void shouldCreateCar() {
        when(carRepository.save(any())).thenReturn(car);
        final Car savedCar = carRepository.save(car);

        assertNotNull(savedCar);
        assertThat(savedCar.getId(), is(car.getId()));
        assertThat(savedCar.getModel(), is(car.getModel()));
        assertThat(savedCar.getBrand(), is(car.getBrand()));
        assertThat(savedCar.getColor(), is(car.getColor()));

        verify(carRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Should delete car")
    void shouldDeleteCar() {
        carRepository.delete(car);

        verify(carRepository, times(1)).delete(any());
    }

    @Test
    @DisplayName("Should get car")
    void shouldGetCar() {
        when(carRepository.findById(any())).thenReturn(Optional.of(car));

        carRepository.findById(4L).ifPresent(car -> {
            assertThat(car.getId(), CoreMatchers.is(car.getId()));
            assertThat(car.getModel(), CoreMatchers.is(car.getModel()));
            assertThat(car.getBrand(), CoreMatchers.is(car.getBrand()));
            assertThat(car.getColor(), CoreMatchers.is(car.getColor()));
        });

        verify(carRepository, times(1)).findById(any());
    }

    @Test
    @DisplayName("Should fail get car when car not found")
    void shouldFailGetCarWhenCarNotFound() {
        when(carRepository.findById(any())).thenThrow(CarNotFoundException.class);

        assertThrows(CarNotFoundException.class, () -> {
            carRepository.findById(4L);
        });
        verify(carRepository, times(1)).findById(any());
    }

    @Test
    @DisplayName("Should list cars")
    void shouldListCars() {
        when(carRepository.findAll()).thenReturn(Collections.singletonList(car));

        final List<Car> cars = carRepository.findAll();

        assertNotNull(cars);
        assertThat(cars.size(), is(1));
        assertThat(cars.get(0).getId(), is(car.getId()));
        assertThat(cars.get(0).getModel(), is(car.getModel()));
        assertThat(cars.get(0).getBrand(), is(car.getBrand()));
        assertThat(cars.get(0).getColor(), is(car.getColor()));

        verify(carRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should list cars when cars not found")
    void shouldListCarsWhenCarsNotFound() {
        when(carRepository.findAll()).thenReturn(Collections.emptyList());

        final List<Car> cars = carRepository.findAll();

        assertNotNull(cars);
        assertThat(cars.size(), is(0));

        verify(carRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should update car")
    void shouldUpdateCar() {
        when(carRepository.save(any())).thenReturn(car);

        final Car updatedCar = carRepository.save(car);

        assertNotNull(updatedCar);
        assertThat(updatedCar.getId(), is(car.getId()));
        assertThat(updatedCar.getModel(), is(car.getModel()));
        assertThat(updatedCar.getBrand(), is(car.getBrand()));
        assertThat(updatedCar.getColor(), is(car.getColor()));

        verify(carRepository, times(1)).save(any());
    }
}
