package com.develhope.spring.vehicles.repository;

import com.develhope.spring.vehicles.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>, JpaSpecificationExecutor<Vehicle> {



    @Query("SELECT new com.develhope.spring.vehicles.entity.MostOrderedVehicleModelCount(v.brand, v.model) FROM Vehicle v right JOIN Order o ON v.id = o.vehicle.id GROUP BY v.model ORDER BY COUNT(o.id) DESC LIMIT 1")
    MostOrderedVehicleModelCount findMostOrderedVehicleModel();
}
