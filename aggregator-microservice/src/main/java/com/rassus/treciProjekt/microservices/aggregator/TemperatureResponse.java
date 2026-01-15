package com.rassus.treciProjekt.microservices.aggregator; // Pazi na package

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "name", "unit", "value" })
public class TemperatureResponse { // Stavi PUBLIC da bude dostupna svuda

    private String name = "Temperature";
    private String unit = "C";
    private double value;

    // PRAZAN KONSTRUKTOR (Nužan za Jackson deserijalizaciju)
    public TemperatureResponse() {
    }


    public TemperatureResponse(double value) {
        this.value = value;
    }

    // Getteri
    public String getName() { return name; }
    public String getUnit() { return unit; }
    public double getValue() { return value; }

    //SETTERI (Nužni za promjenu C u K)
    public void setName(String name) { this.name = name; }
    public void setUnit(String unit) { this.unit = unit; }
    public void setValue(double value) { this.value = value; }
}
