package com.example.task.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class CityNotFoundException extends RuntimeException {
    private String correlationID;
    CityNotFoundException() {

    }
    public CityNotFoundException(Long id, String correlationID) {
        super(String.format("City with Id %d not found", id));
        this.correlationID = correlationID;
    }
}
