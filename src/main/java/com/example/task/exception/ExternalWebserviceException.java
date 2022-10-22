package com.example.task.exception;

public class ExternalWebserviceException extends RuntimeException{
    public ExternalWebserviceException() {

        super("Wystąpił problem z zewnętrznym webserwisem");
    }
}
