package com.example.task.service;

import com.example.task.exception.CityNotFoundException;
import com.example.task.model.City;
import com.example.task.repository.AppRepository;
import com.example.task.utils.JsonUtils;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class WeatherService {
    private RestTemplateService openWeatherService;
    @Value("${config.url}")
    private String baseUrl;
    @Value("${config.appid}")
    private String appId;

    private final AppRepository repository;

    public WeatherService(RestTemplateService openWeatherService, AppRepository repository) {
        this.openWeatherService = openWeatherService;
        this.repository = repository;
    }


    public Object getForecast(List<Long> ids) throws ParseException{
        List<Object> resultRequest = new ArrayList<>();
        for(Long singleID : ids){

                double longitude = repository.getById(singleID).getLongitude();
                double latitude = repository.getById(singleID).getLatitude();

                final String url = baseUrl+"?lat="+latitude +
                        "&lon="+longitude+
                        "&exclude=daily,minutely&units=metric&appid="+appId+"&cnt=1";
                System.out.println(url);

                openWeatherService.setResponseFromOpenWeather(url);

                Object notJSON = openWeatherService.getResponse();

                JSONObject json = JsonUtils.objectToJSONObject(notJSON);
                Map<Object, Object> formatted = JsonUtils.getSiplifiedJson(json);
                resultRequest.add(formatted);



        }


        return resultRequest;
    }
}
