package com.example.task.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Builder
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customWeatherDto", namespace = "http://task.example.com", propOrder = {
        "locationID",
        "description",
        "temperature",
        "location",
        "iconUrl"
})
public class CustomWeatherDto {

    @XmlElement(required = true)
    private Long locationID;
    @XmlElement(required = true)
    private String description;
    @XmlElement(required = true)
    private Double temperature;
    @XmlElement(required = true)
    private String location;
    @XmlElement(required = true)
    private String iconUrl;


}
