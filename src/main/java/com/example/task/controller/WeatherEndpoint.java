//package com.example.task.controller;
//
//import com.example.task.model.GetForecast;
//import com.example.task.model.GetForecastResponse;
//import com.example.task.service.WeatherService;
//import org.springframework.ws.server.endpoint.annotation.Endpoint;
//import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
//import org.springframework.ws.server.endpoint.annotation.RequestPayload;
//import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
//
//@Endpoint
//public class WeatherEndpoint {
//    private final WeatherService service;
//
//    public WeatherEndpoint(WeatherService service) {
//        this.service = service;
//    }
//
//    @PayloadRoot(namespace = "http://task.example.com", localPart = "getForecast")
//    @ResponsePayload
//    public GetForecastResponse getWeatherById(@RequestPayload GetForecast getForecast){
//        GetForecastResponse resp = new GetForecastResponse();
//        resp.setCustomWeatherDto(service.getForecast(getForecast.getIdentity()));
//        System.out.println("Przekazane id: "+getForecast.getIdentity());
//        return resp;
//
//    }
//}
