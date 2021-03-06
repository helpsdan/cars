package com.devnaweb.cars.unit.usecases;

import com.devnaweb.cars.entities.Car;
import com.devnaweb.cars.entities.responses.CarResponse;
import com.devnaweb.cars.fixtures.CarFixture;
import com.devnaweb.cars.repositories.CarRepository;
import com.devnaweb.cars.usecases.CreateCar;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CreateCarUnitTest {

    @InjectMocks
    private CreateCar createCar;

    @Mock
    private CarRepository carRepository;

    private final Car car = CarFixture.DEFAULT_VALUE;

    @Test
    @DisplayName("Should create car")
    void shouldCreateCar() {
        when(carRepository.save(any())).thenReturn(car);

        final CarResponse carResponse = createCar.execute(car);
        assertNotNull(carResponse);
        assertThat(carResponse.getId(), is(car.getId()));
        assertThat(carResponse.getModel(), is(car.getModel()));
        assertThat(carResponse.getBrand(), is(car.getBrand()));
        assertThat(carResponse.getColor(), is(car.getColor()));
        verify(carRepository, times(1)).save(any());
    }
}
