package com.example.task.controller;

import com.example.task.model.City;
import com.example.task.repository.AppRepository;
import com.example.task.service.CitiesService;
import com.example.task.service.WeatherService;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@RestController
public class ForecastRestController {
    private final WeatherService service;

    private final AppRepository repository;

    public ForecastRestController(CitiesService citiesService, WeatherService weatherService, RestTemplate openWeatherMap, AppRepository repository) {
        this.service = weatherService;
        this.repository = repository;
    }
    @GetMapping(path = "/cities")
    public List<City> getLocations() throws ParserConfigurationException, IOException, SAXException {
        return repository.findAll();
    }

    @RequestMapping(value = "/forecast", method = RequestMethod.GET)
    public Object getForecast(@RequestParam(name = "city1") Long id1, @RequestParam(name = "city2", required = false) Long id2, @RequestParam(name = "city3", required = false) Long id3, @RequestParam(name = "city4", required = false) Long id4, @RequestParam(name = "city5", required = false) Long id5)
            throws ParserConfigurationException, IOException, SAXException, ParseException {
        return service.getForecast(id1, id2, id3, id4, id5);
    }
}
