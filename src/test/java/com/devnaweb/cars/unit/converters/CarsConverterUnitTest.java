package com.devnaweb.cars.unit.converters;

import com.devnaweb.cars.entities.Car;
import com.devnaweb.cars.entities.converters.CarsConverter;
import com.devnaweb.cars.entities.responses.CarResponse;
import com.devnaweb.cars.fixtures.CarFixture;
import com.devnaweb.cars.fixtures.CarRequestFixture;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarsConverterUnitTest {

    @Test
    @DisplayName("Should execute to response list when car exists")
    void shouldExecuteToResponseListWhenCarExists() {
        final List<CarResponse> carResponseList =
                CarsConverter.toResponseList(
                        Collections.singletonList(CarFixture.DEFAULT_VALUE));
        assertNotNull(carResponseList);
        assertThat(carResponseList.size(), is(1));
    }

    @Test
    @DisplayName("Should execute to response list when car non exists")
    void shouldExecuteToResponseListWhenCarNonExists() {
        final List<CarResponse> carResponseList =
                CarsConverter.toResponseList(
                        Collections.emptyList());
        assertNotNull(carResponseList);
        assertThat(carResponseList.size(), is(0));
    }

    @Test
    @DisplayName("Should execute to entity when data is valid")
    void shouldExecuteToEntityWhenDataIsValid() {
        final Car car = CarsConverter.toEntity(CarRequestFixture.DEFAULT_VALUE);
        assertNotNull(car);
        assertThat(car.getModel(), is("CIVIC"));
        assertThat(car.getBrand(), is("HONDA"));
        assertThat(car.getColor(), is("White"));
    }

    @Test
    @DisplayName("Should fail execute to entity when car request is null")
    void shouldFailExecuteToEntityWhenCarRequestIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            CarsConverter.toEntity(null);
        });
    }

    @Test
    @DisplayName("Should fail execute to entity when car model is null")
    void shouldFailExecuteToEntityWhenModelNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            CarsConverter.toEntity(CarRequestFixture.DEFAULT_VALUE.toBuilder().model(null).build());
        });
    }

    @Test
    @DisplayName("Should fail execute to entity when car brand is null")
    void shouldFailExecuteToEntityWhenBrandNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            CarsConverter.toEntity(CarRequestFixture.DEFAULT_VALUE.toBuilder().brand(null).build());
        });
    }

    @Test
    @DisplayName("Should fail execute to entity when car color is null")
    void shouldFailExecuteToEntityWhenColorNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            CarsConverter.toEntity(CarRequestFixture.DEFAULT_VALUE.toBuilder().color(null).build());
        });
    }

    @Test
    @DisplayName("Should execute to response when data is valid")
    void shouldExecuteToResponseWhenDataIsValid() {
        final CarResponse carResponse = CarsConverter.toResponse(CarFixture.DEFAULT_VALUE);
        assertNotNull(carResponse);
        assertThat(carResponse.getModel(), is("CIVIC"));
        assertThat(carResponse.getBrand(), is("HONDA"));
        assertThat(carResponse.getColor(), is("White"));
    }

    @Test
    @DisplayName("Should fail execute to response when car request is null")
    void shouldFailExecuteToResponseWhenCarRequestIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            CarsConverter.toResponse(null);
        });
    }

    @Test
    @DisplayName("Should fail execute to response when car model is null")
    void shouldFailExecuteToResponseWhenModelNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            CarsConverter.toResponse(CarFixture.DEFAULT_VALUE.toBuilder().model(null).build());
        });
    }

    @Test
    @DisplayName("Should fail execute to response when car brand is null")
    void shouldFailExecuteToResponseWhenBrandNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            CarsConverter.toResponse(CarFixture.DEFAULT_VALUE.toBuilder().brand(null).build());
        });
    }

    @Test
    @DisplayName("Should fail execute to response when car color is null")
    void shouldFailExecuteToResponseWhenColorNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            CarsConverter.toResponse(CarFixture.DEFAULT_VALUE.toBuilder().color(null).build());
        });
    }
}
