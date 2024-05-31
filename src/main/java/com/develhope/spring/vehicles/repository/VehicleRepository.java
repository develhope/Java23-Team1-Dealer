package com.develhope.spring.vehicles.repository;

import com.develhope.spring.vehicles.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository <Vehicle, Long> {
    List<Vehicle> findByUserId(Long userId);
}
