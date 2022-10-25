package com.example.task.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CustomWeatherDto" type="{http://task.example.com}CustomWeatherDto"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "customWeatherDto"
})
@XmlRootElement(name = "getForecastResponse", namespace = "http://task.example.com")
public class GetForecastResponse {

    @XmlElement(required = true)
    protected CustomWeatherDto customWeatherDto;

    /**
     * Gets the value of the customWeatherDto property.
     *
     * @return
     *     possible object is
     *     {@link CustomWeatherDto }
     *
     */
    public CustomWeatherDto getCustomWeatherDto() {
        return customWeatherDto;
    }

    /**
     * Sets the value of the customWeatherDto property.
     *
     * @param value
     *     allowed object is
     *     {@link CustomWeatherDto }
     *
     */
    public void setCustomWeatherDto(CustomWeatherDto value) {
        this.customWeatherDto = value;
    }

}
