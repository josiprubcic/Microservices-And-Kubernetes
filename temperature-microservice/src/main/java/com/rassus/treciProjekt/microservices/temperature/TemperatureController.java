package com.rassus.treciProjekt.microservices.temperature;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/sensor")
@RestController
public class TemperatureController {

    private final TemperatureReadingService readingService;

    public TemperatureController(TemperatureReadingService readingService){
        this.readingService = readingService;
    }

    @GetMapping("/temp")
    public TemperatureResponse getTemp(){
        return new TemperatureResponse(readingService.getTemperature());
    }

}
