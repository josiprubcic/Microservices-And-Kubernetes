package com.rassus.treciProjekt.microservices.humidity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HumidityRepository extends JpaRepository<HumidityReading, Long> {
}
