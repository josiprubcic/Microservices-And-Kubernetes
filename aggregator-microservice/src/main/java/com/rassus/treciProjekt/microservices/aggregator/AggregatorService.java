package com.rassus.treciProjekt.microservices.aggregator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AggregatorService {
    private final RestTemplate restTemplate;

    @Value("${temperature.url}")
    private String tempUrl;

    @Value("${humidity.url}")
    private String humUrl;

    @Value("${temperature.unit}")
    private String targetUnit;

    public AggregatorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Object> getReadings(){
        TemperatureResponse temp = restTemplate.getForObject(tempUrl, TemperatureResponse.class);

        if (!targetUnit.equals(temp.getUnit())) {
            if(targetUnit.equals("K")){
                double kelvin = temp.getValue() + 273.15;
                temp.setValue(kelvin);
                temp.setUnit("K");
            }
            else{
                double celsius = temp.getValue() - 273.15;
                temp.setValue(celsius);
                temp.setUnit("C");

            }

        }
        // 2. Dohvati vlagu (samo jedan objekt)
        HumidityResponse hum = restTemplate.getForObject(humUrl, HumidityResponse.class);


        return List.of(hum, temp);
    }


}
