package com.rassus.treciProjekt.microservices.humidity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Entity
class HumidityReading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double humValue;



    public HumidityReading() {
    }

    public HumidityReading(double humValue){
        this.humValue = humValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getHumValue() {
        return humValue;
    }

    public void setHumValue(double humValue) {
        this.humValue = humValue;
    }


}
