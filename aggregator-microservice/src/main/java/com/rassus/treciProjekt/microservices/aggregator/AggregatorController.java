package com.rassus.treciProjekt.microservices.aggregator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/")
class AggregatorController {

    private final AggregatorService aggregatorService;

    public AggregatorController(AggregatorService aggregatorService){
        this.aggregatorService = aggregatorService;
    }

    @GetMapping("aggregator")
    public List<Object> getAggregatedReadings() {
        // Kontroler samo zove servis
        return aggregatorService.getReadings();
    }


}
