package com.devnaweb.cars.unit.usecases;

import com.devnaweb.cars.entities.Car;
import com.devnaweb.cars.entities.responses.CarResponse;
import com.devnaweb.cars.fixtures.CarFixture;
import com.devnaweb.cars.repositories.CarRepository;
import com.devnaweb.cars.usecases.ListCars;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ListCarsUnitTest {

    @InjectMocks
    private ListCars listCars;

    @Mock
    private CarRepository carRepository;

    private final Car car = CarFixture.DEFAULT_VALUE;

    @Test
    @DisplayName("Should list car when exist")
    void shouldListCarsWhenExist() {
        when(carRepository.findAll()).thenReturn(Collections.singletonList(car));

        final List<CarResponse> carResponseList = listCars.execute();

        assertNotNull(carResponseList);
        assertThat(carResponseList.size(), is(1));
        assertThat(carResponseList.get(0).getId(), is(4L));
        assertThat(carResponseList.get(0).getModel(), is("CIVIC"));
        assertThat(carResponseList.get(0).getBrand(), is("HONDA"));
        assertThat(carResponseList.get(0).getColor(), is("White"));
    }

    @Test
    @DisplayName("Should list car when non exist")
    void shouldListCarsWhenNonExist() {
        when(carRepository.findAll()).thenReturn(Collections.emptyList());

        final List<CarResponse> carResponseList = listCars.execute();

        assertNotNull(carResponseList);
        assertThat(carResponseList.size(), is(0));
    }
}
