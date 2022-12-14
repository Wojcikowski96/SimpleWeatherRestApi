package com.example.task.clients.model;


import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "customWeatherDto"
})
@XmlRootElement(name = "getForecastResponse", namespace = "http://task.example.com")
public class GetForecastResponse {

    @XmlElement(required = true)
    protected CustomWeatherDto customWeatherDto;



}
