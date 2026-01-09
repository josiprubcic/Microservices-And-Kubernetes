package com.rassus.treciProjekt.microservices.temperature;

import org.springframework.stereotype.Component;

@Component
class TemperatureModel {
    private String name;
    private String unit;
    private Double value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
