package com.example.task.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Builder
@Getter
@Setter
public class CustomWeatherDto {


    private Long locationID;
    private String description;
    private Double temperature;
    private String location;
    private String iconUrl;


}
