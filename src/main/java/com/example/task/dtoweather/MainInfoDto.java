package com.example.task.dtoweather;

import lombok.Getter;

@Getter
public class MainInfoDto {
    private Double temp;

    @Override
    public String toString() {
        return "MainInfoDto{" +
                "temp=" + temp +
                '}';
    }
}
