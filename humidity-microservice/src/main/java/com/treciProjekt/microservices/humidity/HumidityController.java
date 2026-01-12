package com.treciProjekt.microservices.humidity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/sensor")
class HumidityController {

    private HumidityReadingService humidityReadingService;

    public HumidityController(HumidityReadingService humidityReadingService) {
        this.humidityReadingService = humidityReadingService;
    }

    @GetMapping("/humidity")
    public HumidityResponse getHumidity(){return new HumidityResponse(humidityReadingService.getHumidity());}



}
