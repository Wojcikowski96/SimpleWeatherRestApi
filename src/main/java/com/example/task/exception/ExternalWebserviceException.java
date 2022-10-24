package com.example.task.exception;

import org.springframework.stereotype.Component;

@Component
public class ExternalWebserviceException extends RuntimeException{
    public ExternalWebserviceException() {

        super("Wystąpił problem z zewnętrznym webserwisem");
    }
}
