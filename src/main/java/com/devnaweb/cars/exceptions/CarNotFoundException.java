package com.devnaweb.cars.exceptions;

import org.springframework.http.HttpStatus;

public class CarNotFoundException extends BaseBusinessException {

    public CarNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Car not found", false);
    }
}
