package com.example.task.exception;

public class CityNotFoundException extends Exception {

    private int errorCode;
    private String errorMessage;

    public CityNotFoundException(Throwable throwable) {
        super(throwable);
    }

    public CityNotFoundException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public CityNotFoundException(String msg) {
        super(msg);
    }

    public CityNotFoundException(String message, int errorCode) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = message;
    }


    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return this.errorCode + " : " + this.getErrorMessage();
    }
}
