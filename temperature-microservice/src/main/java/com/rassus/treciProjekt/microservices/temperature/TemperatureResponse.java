package com.rassus.treciProjekt.microservices.temperature;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.stereotype.Component;

@JsonPropertyOrder({ "name", "unit", "value" })
class TemperatureResponse {
    private String name = "Temperature";
    private String unit = "C";
    private double value;

    public TemperatureResponse(double value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public double getValue() {
        return value;
    }
}
