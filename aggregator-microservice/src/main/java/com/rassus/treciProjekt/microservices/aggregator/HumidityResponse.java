package com.rassus.treciProjekt.microservices.aggregator; // Pazi na paket!

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "name", "unit", "value" })
public class HumidityResponse {


    private String name = "Humidity";
    private String unit = "%";
    private double value;

    public HumidityResponse() {
    }

    public HumidityResponse(double value) {
        this.value = value;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
}
