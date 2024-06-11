package com.develhope.spring.vehicles.repository;

import com.develhope.spring.vehicles.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository <Vehicle, Long>, JpaSpecificationExecutor<Vehicle> {
}
