package com.devnaweb.cars.integration.usecases;

import com.devnaweb.cars.entities.responses.CarResponse;
import com.devnaweb.cars.repositories.CarRepository;
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

    @Test
    @DisplayName("Should list cars when exist")
    void shouldListCarsWhenExist() {
        final List<CarResponse> cars = listCars.execute();

        assertThat(cars.size(), is(3));
        assertThat(cars.get(0).getId(), is(1L));
        assertThat(cars.get(0).getModel(), is("X1"));
        assertThat(cars.get(0).getBrand(), is("BMW"));
        assertThat(cars.get(0).getColor(), is("Black"));
    }
}
