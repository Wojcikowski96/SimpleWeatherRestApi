package com.example.task.service;


import com.example.task.dtoweather.OpenWeatherDtoWeather;
import com.example.task.exception.MyResponseErrorHandler;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class RestTemplateService {
    private final RestTemplate openWeatherMap;
    private OpenWeatherDtoWeather response;

    public RestTemplateService(RestTemplateBuilder restTemplateBuilder, MyResponseErrorHandler myResponseErrorHandler) {
        this.openWeatherMap = restTemplateBuilder
                .errorHandler(myResponseErrorHandler)
                .build();
    }
    public void setResponseFromOpenWeather(String url){
        response = this.openWeatherMap.getForObject(url, OpenWeatherDtoWeather.class, SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public OpenWeatherDtoWeather getResponse() {
        return response;
    }

}
