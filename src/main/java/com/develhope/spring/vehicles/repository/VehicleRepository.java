package com.develhope.spring.vehicles.repository;

import com.develhope.spring.vehicles.entity.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository <Vehicles, Long> {
}
