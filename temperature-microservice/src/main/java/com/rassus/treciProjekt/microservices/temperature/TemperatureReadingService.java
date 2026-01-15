package com.rassus.treciProjekt.microservices.temperature;

import jakarta.annotation.PostConstruct;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TemperatureReadingService {

    @Value("${data.filepath:classpath:readings.csv}")
    private Resource dataResource;


    private final TemperatureRepository repository;

    public TemperatureReadingService(TemperatureRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        try {

            List<TemperatureReading> tempReadings = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(dataResource.getInputStream(), StandardCharsets.UTF_8))) {

                String line;
                boolean isFirstLine = true;

                while ((line = reader.readLine()) != null) {
                    // Preskače header (prvi red)
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue;
                    }

                    String[] values = line.split(",");
                    if (values.length > 0) {
                        try {
                            Double tempValue = Double.parseDouble(values[0].trim());
                            tempReadings.add(new TemperatureReading(tempValue));
                        } catch (NumberFormatException e) {
                            System.err.println("Greška u parsiranju broja: " + values[0]);
                        }
                    }
                }
            }

            repository.saveAll(tempReadings);
            System.out.println("Uspjesno spremljeno :) Učitano zapisa: " + tempReadings.size());

        } catch (IOException e) {
            System.err.println("Kritična greška: Ne mogu pročitati CSV datoteku: " + dataResource.getFilename());
            e.printStackTrace();
        }
    }



    public double getTemperature() {



        long index = calculateTargetRow();

            Optional<TemperatureReading> reading = repository.findById(index);

            if (reading.isPresent()){
                return reading.get().getTempValue();
            }else{
                System.err.println("Upozorenje: Nema očitanja za ID " + index);
                return 0.0;
            }



    }

    private int calculateTargetRow() {
        // Formula iz zadatka: red <- (brojAktivnihSekundi % 100) + 1
        // "Varijabla brojAktivnihSekundi je razlika između trenutnog vremena i ponoći, 1. siječnja 1970... mjerena u minutama"

        long currentMillis = System.currentTimeMillis();
        long minutesSinceEpoch = currentMillis / (1000 * 60);


        long brojAktivnihSekundi = minutesSinceEpoch;

        return (int) ((brojAktivnihSekundi % 100) + 1);
    }
}





