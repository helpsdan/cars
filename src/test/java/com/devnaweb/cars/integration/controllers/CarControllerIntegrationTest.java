package com.devnaweb.cars.integration.controllers;

import com.devnaweb.cars.usecases.CreateCar;
import com.devnaweb.cars.usecases.DeleteCar;
import com.devnaweb.cars.usecases.GetCar;
import com.devnaweb.cars.usecases.ListCars;
import com.devnaweb.cars.usecases.UpdateCar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

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

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(this)
                .setValidator(new LocalValidatorFactoryBean())
                .build();
    }

    @Test
    @DisplayName("Should create car with valid data")
    void shouldCreateCarWithValidData() {

    }

    @Test
    @DisplayName("Should fail create car when model is null")
    void shouldFailCreateCarWhenModelIsNull() {

    }

    @Test
    @DisplayName("Should fail create car when brand is null")
    void shouldFailCreateCarWhenBrandIsNull() {

    }

    @Test
    @DisplayName("Should fail create car when color is null")
    void shouldFailCreateCarWhenColorIsNull() {

    }

    @Test
    @DisplayName("Should delete car when car exists")
    void shouldDeleteCarWhenCarExists() {

    }

    @Test
    @DisplayName("Should fail delete non existent car")
    void shouldFailDeleteNonExistentCar() {

    }

    @Test
    @DisplayName("Should get existent car")
    void shouldGetCarWhenCarExists() {

    }

    @Test
    @DisplayName("Should fail get non existent car")
    void shouldFailGetNonExistentCar() {

    }

    @Test
    @DisplayName("Should list cars when cars exists")
    void shouldListCarsWhenExistCars() {

    }

    @Test
    @DisplayName("Should update car with valid data")
    void shouldUpdateCarWithValidData() {

    }

    @Test
    @DisplayName("Should fail update car when car not found")
    void shouldFailUpdateCarWhenCarNotFound() {

    }

    @Test
    @DisplayName("Should fail update car when model is null")
    void shouldFailUpdateCarWhenModelIsNull() {

    }

    @Test
    @DisplayName("Should fail update car when brand is null")
    void shouldFailUpdateCarWhenBrandIsNull() {

    }

    @Test
    @DisplayName("Should fail update car when color is null")
    void shouldFailUpdateCarWhenColorIsNull() {

    }
}
