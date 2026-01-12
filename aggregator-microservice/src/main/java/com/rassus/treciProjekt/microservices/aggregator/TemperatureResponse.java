package com.rassus.treciProjekt.microservices.aggregator; // Pazi na package

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "name", "unit", "value" })
public class TemperatureResponse { // Stavi PUBLIC da bude dostupna svuda

    private String name = "Temperature";
    private String unit = "C";
    private double value;

    // 1. PRAZAN KONSTRUKTOR (Nužan za Jackson deserijalizaciju)
    public TemperatureResponse() {
    }

    // Konstruktor s vrijednostima (opcionalno, ali korisno)
    public TemperatureResponse(double value) {
        this.value = value;
    }

    // Getteri
    public String getName() { return name; }
    public String getUnit() { return unit; }
    public double getValue() { return value; }

    // 2. SETTERI (Nužni da možeš promijeniti C u K)
    public void setName(String name) { this.name = name; }
    public void setUnit(String unit) { this.unit = unit; }
    public void setValue(double value) { this.value = value; }
}
