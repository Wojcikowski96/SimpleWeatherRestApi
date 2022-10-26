package com.example.task.forecastbuilder;

import com.example.task.clients.model.*;
import com.example.task.dtoweather.OpenWeatherDtoWeather;
import com.example.task.exception.CityNotFoundException;
import com.example.task.repository.AppRepository;
import com.example.task.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;
@Component
public class ForecastBuilder {
    @Value("${config.url}")
    private String baseUrl;
    @Value("${config.appid}")
    private String appId;

    @Value("${config.iconUrl}")
    private String iconbaseUrl;
    @Value("${config.correlationID}")
    private String correlationID;
    private AppRepository repository;
    ForecastBuilder(AppRepository repository){
        this.repository = repository;
    }
    public CustomWeatherDto buildForecast(GetForecast getForecast, RestTemplateService openWeatherService){

        String correlationId = RequestFlowData.getId();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(RequestFlowData.CORRELATION_ID, correlationId);


        Long locationID = getForecast.getCityID();
        repository.findById(getForecast.getCityID())
                .orElseThrow(() -> new CityNotFoundException(locationID));

        double longitude = repository.findById(locationID).map(City::getLongitude).orElse(null);
        double latitude = repository.findById(locationID).map(City::getLatitude).orElse(null);


        final String url = baseUrl+"?lat="+latitude +
                "&lon="+longitude+
                "&exclude=daily,minutely&units=metric&appid="+appId+"&cnt=1&lang=pl";
        System.out.println(url);

        openWeatherService.setHttpHeaders(httpHeaders);

        openWeatherService.setResponseFromOpenWeather(url);

        OpenWeatherDtoWeather openWeatherServiceResponse = openWeatherService.getResponse();

        CustomWeatherDto formatted = CustomWeatherDto.builder().temperature(openWeatherServiceResponse.getMain()
                        .getTemp()).description(openWeatherServiceResponse.getWeather().get(0).getDescription())
                .location(openWeatherServiceResponse.getName()).locationID(locationID)
                .iconUrl(iconbaseUrl+openWeatherServiceResponse.getWeather().get(0).getIcon()+".png").correlationID(correlationId).build();
        return formatted;
    }
}
