package com.example.task.service;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class RestTemplateService {
    private final RestTemplate openWeatherMap;
    private Object response;

    public RestTemplateService(RestTemplate openWeatherMap) {
        this.openWeatherMap = openWeatherMap;
    }
    public void setResponseFromOpenWeather(String url){
        response = this.openWeatherMap.getForObject(url, Object.class);
    }

    public Object getResponse() {
        return response;
    }

}
