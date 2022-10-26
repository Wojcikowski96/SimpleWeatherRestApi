package com.example.task.clients;

import com.example.task.clients.model.*;
import com.example.task.dtoweather.OpenWeatherDtoWeather;
import com.example.task.exception.CityNotFoundException;
import com.example.task.repository.AppRepository;
import com.example.task.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;
@Component
@Endpoint
public class Client {
    private RestTemplateService openWeatherService;
    @Value("${config.url}")
    private String baseUrl;
    @Value("${config.appid}")
    private String appId;

    @Value("${config.iconUrl}")
    private String iconbaseUrl;
    @Value("${config.correlationID}")
    private String correlationID;
    private final AppRepository repository;

    public Client(RestTemplateService openWeatherService, AppRepository repository) {
        this.openWeatherService = openWeatherService;
        this.repository = repository;
    }
    @PayloadRoot(namespace = "http://task.example.com", localPart = "getForecast")
    @ResponsePayload
    public GetForecastResponse getForecast(@RequestPayload GetForecast getForecast){
        String correlationId = RequestFlowData.getId();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(RequestFlowData.CORRELATION_ID, correlationId);

        LOGGER.info("start REST request to {} with correlationId {}"+ correlationId);
        LOGGER.info("nagłówek"+ httpHeaders.get("X-Correlation-ID"));

        GetForecastResponse resp = new GetForecastResponse();
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
        resp.setCustomWeatherDto(formatted);
        return resp;}
}


