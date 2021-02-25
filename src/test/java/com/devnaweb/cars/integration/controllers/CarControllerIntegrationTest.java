package com.devnaweb.cars.integration.controllers;

import com.devnaweb.cars.entities.converters.CarsConverter;
import com.devnaweb.cars.entities.requests.CarRequest;
import com.devnaweb.cars.fixtures.CarRequestFixture;
import com.devnaweb.cars.fixtures.CarResponseFixture;
import com.devnaweb.cars.usecases.CreateCar;
import com.devnaweb.cars.usecases.DeleteCar;
import com.devnaweb.cars.usecases.GetCar;
import com.devnaweb.cars.usecases.ListCars;
import com.devnaweb.cars.usecases.UpdateCar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class CarControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateCar createCar;

    @MockBean
    private DeleteCar deleteCar;

    @MockBean
    private GetCar getCar;

    @MockBean
    private ListCars listCars;

    @MockBean
    private UpdateCar updateCar;

    private String mapToJson(final Object obj) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    @Test
    @DisplayName("Should create car with valid data")
    void shouldCreateCarWithValidData() throws Exception {
        final CarRequest car = CarRequestFixture.DEFAULT_VALUE;

        mockMvc.perform(post("/api/car/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(car))
                .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    @DisplayName("Should fail create car when model is null")
    void shouldFailCreateCarWhenModelIsNull() throws Exception {
        final CarRequest car = CarRequestFixture.DEFAULT_VALUE.toBuilder().model(null).build();

        assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(post("/api/car/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapToJson(car))
                    .characterEncoding("utf-8"))
                    .andReturn();
        });
    }

    @Test
    @DisplayName("Should fail create car when brand is null")
    void shouldFailCreateCarWhenBrandIsNull() {
        final CarRequest car = CarRequestFixture.DEFAULT_VALUE.toBuilder().brand(null).build();

        assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(post("/api/car/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapToJson(car))
                    .characterEncoding("utf-8"))
                    .andReturn();
        });
    }

    @Test
    @DisplayName("Should fail create car when color is null")
    void shouldFailCreateCarWhenColorIsNull() {
        final CarRequest car = CarRequestFixture.DEFAULT_VALUE.toBuilder().color(null).build();

        assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(post("/api/car/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapToJson(car))
                    .characterEncoding("utf-8"))
                    .andReturn();
        });
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
    @DisplayName("Should get existent car")
    void shouldGetCarWhenCarExists() throws Exception {
        mockMvc.perform(get("/api/{id}", "4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("Should list cars when cars exists")
    void shouldListCarsWhenExistCars() throws Exception {
        mockMvc.perform(get("/api/cars")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should update car with valid data")
    void shouldUpdateCarWithValidData() throws Exception {
        final CarRequest car = CarRequestFixture.DEFAULT_VALUE;

        mockMvc.perform(put("/api/{id}", "4")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(car))
                .characterEncoding("utf-8"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should fail update car when model is null")
    void shouldFailUpdateCarWhenModelIsNull() {
        final CarRequest car = CarRequestFixture.DEFAULT_VALUE.toBuilder().model(null).build();

        assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(put("/api/{id}", "4")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapToJson(car))
                    .characterEncoding("utf-8"));
        });
    }

    @Test
    @DisplayName("Should fail update car when brand is null")
    void shouldFailUpdateCarWhenBrandIsNull() {
        final CarRequest car = CarRequestFixture.DEFAULT_VALUE.toBuilder().brand(null).build();

        assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(put("/api/{id}", "4")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapToJson(car))
                    .characterEncoding("utf-8"));
        });
    }

    @Test
    @DisplayName("Should fail update car when color is null")
    void shouldFailUpdateCarWhenColorIsNull() {
        final CarRequest car = CarRequestFixture.DEFAULT_VALUE.toBuilder().color(null).build();

        assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(put("/api/{id}", "4")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapToJson(car))
                    .characterEncoding("utf-8"));
        });
    }
}
