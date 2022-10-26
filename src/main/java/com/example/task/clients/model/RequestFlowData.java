package com.example.task.clients.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "correlationId"
})
@XmlRootElement(name = "RequestFlowData", namespace = "http://task.example.com")
public class RequestFlowData {
    RequestFlowData(){

    }
    public static final String CORRELATION_ID = "CorrelationId";

    private static final ThreadLocal<String> id = new ThreadLocal<String>();

    public static String getId() { return id.get(); }

    public static void setId(String correlationId) { id.set(correlationId); }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
