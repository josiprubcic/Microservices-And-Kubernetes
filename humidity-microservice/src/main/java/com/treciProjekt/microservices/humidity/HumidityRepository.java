package com.treciProjekt.microservices.humidity;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface HumidityRepository extends JpaRepository<HumidityReading, Long> {
}
