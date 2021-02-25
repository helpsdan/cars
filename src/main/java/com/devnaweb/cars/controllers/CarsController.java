package com.devnaweb.cars.controllers;

import com.devnaweb.cars.entities.converters.CarsConverter;
import com.devnaweb.cars.entities.requests.CarRequest;
import com.devnaweb.cars.entities.responses.CarResponse;
import com.devnaweb.cars.usecases.CreateCar;
import com.devnaweb.cars.usecases.DeleteCar;
import com.devnaweb.cars.usecases.GetCar;
import com.devnaweb.cars.usecases.ListCars;
import com.devnaweb.cars.usecases.UpdateCar;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class CarsController {

    @Autowired
    private ListCars listCars;

    @Autowired
    private CreateCar createCar;

    @Autowired
    private UpdateCar updateCar;

    @Autowired
    private DeleteCar deleteCar;

    @Autowired
    private GetCar getCar;

    @GetMapping("/cars")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CarResponse>> listCars() {
        log.info("CarsController: list cars");
        return ResponseEntity.status(HttpStatus.OK).body(listCars.execute());
    }

    @PostMapping("/car/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CarResponse> createCar(@RequestBody final CarRequest carRequest) {
        log.info("CarsController: create car");
        return ResponseEntity.status(HttpStatus.CREATED).body(createCar.execute(CarsConverter.toEntity(carRequest)));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CarResponse> updateCar(@RequestBody final CarRequest carRequest,
                                                 @PathVariable final long id) {
        log.info("CarsController: update car");
        return ResponseEntity.status(HttpStatus.OK).body(updateCar.execute(id, CarsConverter.toEntity(carRequest)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteCar(@PathVariable final long id) {
        log.info("CarsController: delete car");
        deleteCar.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CarResponse> getCar(@PathVariable final long id) {
        log.info("CarsController: get car");
        return ResponseEntity.status(HttpStatus.OK).body(getCar.execute(id));
    }
}
