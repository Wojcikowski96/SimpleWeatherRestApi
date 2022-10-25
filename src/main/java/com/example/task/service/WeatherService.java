package com.example.task.service;

import com.example.task.dtoweather.OpenWeatherDtoWeather;
import com.example.task.exception.CityNotFoundException;
import com.example.task.model.City;
import com.example.task.model.CustomWeatherDto;
import com.example.task.repository.AppRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;


@Service

public class WeatherService {
    private RestTemplateService openWeatherService;
    @Value("${config.url}")
    private String baseUrl;
    @Value("${config.appid}")
    private String appId;

    @Value("${config.iconUrl}")
    private String iconbaseUrl;
    private final AppRepository repository;

    public WeatherService(RestTemplateService openWeatherService, AppRepository repository) {
        this.openWeatherService = openWeatherService;
        this.repository = repository;
    }


    public  List<CustomWeatherDto> getMultipleForecast(List<Long> ids){
        List<CustomWeatherDto> resultRequest = new ArrayList<>();

        for(Long singleID : ids){
                  resultRequest.add(getForecast(singleID));
        }

        return resultRequest;
    }


    public CustomWeatherDto getForecast(Long singleID){

        repository.findById(singleID)
                .orElseThrow(() -> new CityNotFoundException(singleID));

        double longitude = repository.findById(singleID).map(City::getLongitude).orElse(null);
        double latitude = repository.findById(singleID).map(City::getLatitude).orElse(null);


        final String url = baseUrl+"?lat="+latitude +
                "&lon="+longitude+
                "&exclude=daily,minutely&units=metric&appid="+appId+"&cnt=1&lang=pl";
        System.out.println(url);

        openWeatherService.setResponseFromOpenWeather(url);

        OpenWeatherDtoWeather openWeatherServiceResponse = openWeatherService.getResponse();

        CustomWeatherDto formatted = CustomWeatherDto.builder().temperature(openWeatherServiceResponse.getMain()
                        .getTemp()).description(openWeatherServiceResponse.getWeather().get(0).getDescription())
                .location(openWeatherServiceResponse.getName()).locationID(singleID)
                .iconUrl(iconbaseUrl+openWeatherServiceResponse.getWeather().get(0).getIcon()+".png").build();
        return formatted;

    }
}
