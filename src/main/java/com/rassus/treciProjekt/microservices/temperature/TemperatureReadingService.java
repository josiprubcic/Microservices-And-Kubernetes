package com.rassus.treciProjekt.microservices.temperature;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Double.parseDouble;

@Service
public class TemperatureReadingService {
    // Ovo omogućuje da se putanja mijenja kroz application.properties
    // properties: data.filepath=src/main/resources/readings.csv (za lokalno)
    // Za Docker će biti: data.filepath=/app/config/readings.csv
    @Value("${data.filepath:readings.csv}")
    private String filePath;
    private final TemperatureRepository repository;

    public TemperatureReadingService(TemperatureRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init(){
        try{
            List<String> lines = Files.readAllLines(Path.of(filePath));
            List<TemperatureReading> tempReadings = new ArrayList<>();

            for (int i = 1; i < lines.size(); i++){
                String line = lines.get(i);
                String[] values = line.split(",");

                if (values.length > 0){
                    try{
                        Double tempValue = Double.parseDouble(values[0]);
                        tempReadings.add(new TemperatureReading(tempValue));
                    }
                    catch (NumberFormatException e){
                        System.err.println("Preskačem neispravan redak " + i);
                    }
                }
            }

            repository.saveAll(tempReadings);
            System.out.println("Uspjesno spremljeno :)");
        }
        catch (IOException e){
            System.err.println("Kritična greška: Ne mogu pročitati CSV datoteku!");
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





