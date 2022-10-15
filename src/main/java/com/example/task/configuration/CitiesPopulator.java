package com.example.task.configuration;

import com.example.task.model.City;
import com.example.task.repository.AppRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class CitiesPopulator {
    private  List<City> cities = new ArrayList<>();
    private AppRepository repository;
    private CitiesMiner miner;

    CitiesPopulator(CitiesMiner citiesMiner, AppRepository repository){
        this.miner = citiesMiner;
        this.repository = repository;
    }
    @Bean
    public void populateCities() throws ParserConfigurationException, IOException, SAXException {
        cities = CitiesMiner.mineData();
        for(City city: cities){
            repository.save(city);
        }
    }

}
