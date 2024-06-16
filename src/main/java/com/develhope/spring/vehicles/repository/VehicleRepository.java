package com.develhope.spring.vehicles.repository;

import com.develhope.spring.vehicles.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository <Vehicle, Long>, JpaSpecificationExecutor<Vehicle> {
    @Query ("SELECT v.model, COUNT(v.model) FROM Vehicle v WHERE v.vehicleState = PURCHASED GROUP BY v.model ORDER BY COUNT(v.model) DESC")
    Collection<Integer> findMostPurchasedVehicleModel();
    //int countByVehicleState(VehicleState vehicleState);
    //Collection<String> findAllModelsGroupByModel ();
}
