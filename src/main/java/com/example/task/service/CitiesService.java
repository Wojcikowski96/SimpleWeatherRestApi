package com.example.task.service;

import com.example.task.configuration.CitiesPopulator;
import com.example.task.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
@Service
public class CitiesService{
    private final CitiesPopulator populator;

    CitiesService(CitiesPopulator populator){
        this.populator = populator;
    }




}
