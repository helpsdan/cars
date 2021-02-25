package com.devnaweb.cars.unit.usecases;

import com.devnaweb.cars.entities.Car;
import com.devnaweb.cars.exceptions.CarNotFoundException;
import com.devnaweb.cars.fixtures.CarFixture;
import com.devnaweb.cars.repositories.CarRepository;
import com.devnaweb.cars.usecases.DeleteCar;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DeleteCarUnitTest {

    @InjectMocks
    private DeleteCar deleteCar;

    @Mock
    private CarRepository carRepository;

    private final Car car = CarFixture.DEFAULT_VALUE;

    @Test
    @DisplayName("Should delete car")
    void shouldDeleteCarWhenExists() {
        when(carRepository.findById(any())).thenReturn(Optional.of(car));

        deleteCar.execute(4L);

        verify(carRepository, times(1)).findById(any());
        verify(carRepository, times(1)).delete(any());
    }

    @Test
    @DisplayName("Should fail when delete non existent car")
    void shouldFailDeleteCarWhenCarNotFound() {
        when(carRepository.findById(any())).thenThrow(CarNotFoundException.class);

        assertThrows(CarNotFoundException.class, () -> {
            deleteCar.execute(4L);
        });

        verify(carRepository, times(1)).findById(any());
        verify(carRepository, times(0)).delete(any());
    }
}
