package com.devnaweb.cars.unit.controllers;

import com.devnaweb.cars.controllers.CarsController;
import com.devnaweb.cars.entities.Car;
import com.devnaweb.cars.entities.converters.CarsConverter;
import com.devnaweb.cars.entities.requests.CarRequest;
import com.devnaweb.cars.exceptions.CarNotFoundException;
import com.devnaweb.cars.fixtures.CarFixture;
import com.devnaweb.cars.fixtures.CarRequestFixture;
import com.devnaweb.cars.fixtures.CarResponseFixture;
import com.devnaweb.cars.usecases.CreateCar;
import com.devnaweb.cars.usecases.DeleteCar;
import com.devnaweb.cars.usecases.GetCar;
import com.devnaweb.cars.usecases.ListCars;
import com.devnaweb.cars.usecases.UpdateCar;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.util.NestedServletException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class CarControllerUnitTest extends AbstractControllerTest {

    @InjectMocks
    private CarsController carsController;

    @Mock
    private CreateCar createCar;

    @Mock
    private DeleteCar deleteCar;

    @Mock
    private GetCar getCar;

    @Mock
    private ListCars listCars;

    @Mock
    private UpdateCar updateCar;

    @BeforeEach
    void setUp() {
        this.setUp(carsController);
    }

    @Test
    @DisplayName("Should create car with valid data")
    void shouldCreateCarWithValidData() throws Exception {
        final Car car = CarFixture.DEFAULT_VALUE;
        when(createCar.execute(any())).thenReturn(CarResponseFixture.DEFAULT_VALUE);

        mockMvc.perform(post("/api/car/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(car))
                .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(4L))
                .andExpect(jsonPath("$.model").value("CIVIC"))
                .andExpect(jsonPath("$.brand").value("HONDA"))
                .andExpect(jsonPath("$.color").value("White"))
                .andReturn();

        verify(createCar, times(1)).execute(any());
    }

    @Test
    @DisplayName("Should fail create car when model is null")
    void shouldFailCreateCarWhenModelIsNull() throws Exception {
        final Car car = CarFixture.DEFAULT_VALUE.toBuilder().model(null).build();
        when(createCar.execute(any())).thenReturn(CarResponseFixture.DEFAULT_VALUE);

        assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(post("/api/car/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapToJson(car))
                    .characterEncoding("utf-8"))
                    .andReturn();
        });
        verify(createCar, times(0)).execute(any());
    }

    @Test
    @DisplayName("Should fail create car when brand is null")
    void shouldFailCreateCarWhenBrandIsNull() {
        final Car car = CarFixture.DEFAULT_VALUE.toBuilder().brand(null).build();
        when(createCar.execute(any())).thenReturn(CarResponseFixture.DEFAULT_VALUE);

        assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(post("/api/car/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapToJson(car))
                    .characterEncoding("utf-8"))
                    .andReturn();
        });
        verify(createCar, times(0)).execute(any());
    }

    @Test
    @DisplayName("Should fail create car when color is null")
    void shouldFailCreateCarWhenColorIsNull() {
        final Car car = CarFixture.DEFAULT_VALUE.toBuilder().color(null).build();
        when(createCar.execute(any())).thenReturn(CarResponseFixture.DEFAULT_VALUE);

        assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(post("/api/car/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapToJson(car))
                    .characterEncoding("utf-8"))
                    .andReturn();
        });
        verify(createCar, times(0)).execute(any());
    }

    @Test
    @DisplayName("Should delete car when car exists")
    void shouldDeleteCarWhenCarExists() throws Exception {
        mockMvc.perform(delete("/api/{id}", "4")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Should get car when car exists")
    void shouldGetCarWhenCarExists() throws Exception {
        final CarRequest car = CarRequestFixture.DEFAULT_VALUE;
        when(getCar.execute(4L)).thenReturn(CarResponseFixture.DEFAULT_VALUE);

        mockMvc.perform(get("/api/{id}", "4")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(car)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("Should fail get non existent car")
    void shouldFailGetNonExistentCar() {
        final CarRequest car = CarRequestFixture.DEFAULT_VALUE;
        when(getCar.execute(4L)).thenThrow(CarNotFoundException.class);

        assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(get("/api/{id}", "4")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapToJson(car)));
        });
    }

    @Test
    @DisplayName("Should list cars when cars exist")
    void shouldListCarsWhenExistCars() throws Exception {
        when(listCars.execute()).thenReturn(Collections.singletonList(CarResponseFixture.DEFAULT_VALUE));

        mockMvc.perform(get("/api/cars")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should not list cars when cars non exists")
    void shouldNotListCarsWhenNonExistentCars() throws Exception {
        when(listCars.execute()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/cars")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should update car with valid data")
    void shouldUpdateCarWithValidData() throws Exception {
        final CarRequest car = CarRequestFixture.DEFAULT_VALUE;
        when(updateCar.execute(4L, CarsConverter.toEntity(car))).thenReturn(CarResponseFixture.DEFAULT_VALUE);

        mockMvc.perform(put("/api/{id}", "4")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(car))
                .characterEncoding("utf-8"))
                .andExpect(status().isOk());
    }
}
