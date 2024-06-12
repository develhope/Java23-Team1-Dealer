package com.develhope.spring.vehicles.repository;

import com.develhope.spring.vehicles.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository <Vehicle, Long>, JpaSpecificationExecutor<Vehicle> {

    @Query("SELECT v.model FROM Vehicle v WHERE v.id = (SELECT o.vehicle.id FROM Order o GROUP BY o.vehicle.id ORDER BY COUNT(o.id) DESC)")
    String findMostOrderedVehicleModel();
}
