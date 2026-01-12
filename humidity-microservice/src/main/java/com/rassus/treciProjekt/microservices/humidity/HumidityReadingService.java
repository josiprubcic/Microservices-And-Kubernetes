package com.rassus.treciProjekt.microservices.humidity;

import jakarta.annotation.PostConstruct;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
class HumidityReadingService {
    @Value("${data.filepath:classpath:readings.csv}")
    private Resource dataResource;

    private final HumidityRepository repository ;


    HumidityReadingService(HumidityRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        try {
            // PROMJENA 2: Čitamo kao Stream (radi i u JAR-u i u Dockeru)
            List<HumidityReading> humidityReadings = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(dataResource.getInputStream(), StandardCharsets.UTF_8))) {

                String line;
                boolean isFirstLine = true;

                while ((line = reader.readLine()) != null) {
                    // Preskačemo header (prvi red)
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue;
                    }

                    String[] values = line.split(",");
                    if (values.length > 0) {
                        try {
                            // Trim miče praznine ako ih slučajno ima
                            Double tempValue = Double.parseDouble(values[2].trim());
                            humidityReadings.add(new HumidityReading(tempValue));
                        } catch (NumberFormatException e) {
                            System.err.println("Greška u parsiranju broja: " + values[0]);
                        }
                    }
                }
            }

            repository.saveAll(humidityReadings);
            System.out.println("Uspjesno spremljeno :) Učitano zapisa: " + humidityReadings.size());

        } catch (IOException e) {
            System.err.println("Kritična greška: Ne mogu pročitati CSV datoteku: " + dataResource.getFilename());
            e.printStackTrace();
        }
    }

    public double getHumidity() {



        long index = calculateTargetRow();

        Optional<HumidityReading> reading = repository.findById(index);

        if (reading.isPresent()){
            return reading.get().getHumValue();
        }else{
            System.err.println("Upozorenje: Nema očitanja za ID " + index);
            return 0.0;
        }



    }

    private int calculateTargetRow() {
        // Formula iz zadatka: red ← (brojAktivnihSekundi % 100) + 1
        // "Varijabla brojAktivnihSekundi je razlika između trenutnog vremena i ponoći, 1. siječnja 1970... mjerena u minutama"

        long currentMillis = System.currentTimeMillis();
        long minutesSinceEpoch = currentMillis / (1000 * 60);

        // Iako se varijabla zove "brojAktivnihSekundi", zadatak kaže da je u minutama.
        // Zadržimo ime varijable iz zadatka radi jasnoće, ali znamo da su to minute.
        long brojAktivnihSekundi = minutesSinceEpoch;

        return (int) ((brojAktivnihSekundi % 100) + 1);
    }


}
