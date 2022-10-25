package com.example.task.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",  propOrder = {
        "identity"
})
@XmlRootElement(name = "getForecast", namespace = "http://task.example.com")
public class GetForecast {

    protected long identity;

    /**
     * Gets the value of the identity property.
     *
     */
    public long getIdentity() {
        return identity;
    }

    /**
     * Sets the value of the identity property.
     *
     */
    public void setIdentity(long value) {
        this.identity = value;
    }

}
