package com.example.task.service;

import com.example.task.repository.AppRepository;
import com.example.task.utils.JsonUtils;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class WeatherService {
    private final RestTemplate openWeatherMap;

    private final AppRepository repository;

    public WeatherService(RestTemplate openWeatherMap, AppRepository repository) {
        this.openWeatherMap = openWeatherMap;
        this.repository = repository;
    }


    public Object getForecast(Long id1, Long id2, Long id3, Long id4, Long id5) throws ParseException {
        List<Long> citiesIds = new ArrayList<>(Arrays.asList(id1, id2, id3, id4, id5));
        List<Object> resultRequest = new ArrayList<>();
        for(Long singleID : citiesIds){
            if(singleID!=null){
                Double longitude = repository.getById(singleID).getLongitude();
                Double latitude = repository.getById(singleID).getLatitude();

                final String url = "https://api.openweathermap.org/data/2.5/weather?lat="+latitude +
                        "&lon="+longitude+
                        "&exclude=hourly,daily,minutely&units=metric&appid=93836445ebca1a81c1081e8b9fa753f0&cnt=4";
               Object notJSON = this.openWeatherMap.getForObject(url, Object.class);
                JSONObject json = JsonUtils.objectToJSONObject(notJSON);
                Map<Object, Object> formatted = JsonUtils.getSiplifiedJson(json);
                resultRequest.add(formatted);
            }

        }


        return resultRequest;
    }
}
