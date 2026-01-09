package com.rassus.treciProjekt.microservices.temperature;

import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
@Service
class TemperatureService {
    String filePath = "readings.csv";
    String line;
    String delimiter = ",";
    Long currentTime;
    public TemperatureModel getTemperature() {

        currentTime = System.currentTimeMillis();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                for (String value : values) {
                    System.out.println(value + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}




}
