package com.rassus.treciProjekt.microservices.temperature;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/sensor")
@RestController
public class temperatureController {

    @GetMapping("/temp")
    public Temp

}
