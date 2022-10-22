package com.example.task.controller;

import com.example.task.model.City;
import com.example.task.model.CustomWeatherDto;
import com.example.task.repository.AppRepository;
import com.example.task.service.WeatherService;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RestController {
    private final WeatherService service;
    private final AppRepository repository;

    public RestController(WeatherService weatherService, RestTemplate openWeatherMap,
                          AppRepository repository) {
        this.service = weatherService;
        this.repository = repository;
    }
    @GetMapping(path = "/cities")
    public Iterable<City> getLocations() throws ParserConfigurationException, IOException, SAXException {
        return repository.findAll();
    }

    @RequestMapping(value = "/forecast", method = RequestMethod.GET)
    public List<CustomWeatherDto> getForecast(@RequestParam(name="cityID") List<Long> idS)
            throws ParseException{


        return service.getForecast(idS);
    }
}
