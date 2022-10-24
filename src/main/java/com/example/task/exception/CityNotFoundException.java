package com.example.task.exception;

import org.springframework.stereotype.Component;

@Component
public class CityNotFoundException extends RuntimeException {
    CityNotFoundException(){

    }

    public CityNotFoundException(Long id) {

        super(String.format("City with Id %d not found", id));
    }
}
