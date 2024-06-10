package com.develhope.spring.vehicles.repository;

import com.develhope.spring.vehicles.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository <Vehicle, Long>, JpaSpecificationExecutor<Vehicle> {

    List<Vehicle> findByVehicleKind (VehicleKind vehicleKind);

    List<Vehicle> findByBrandContaining (String brand);

    List<Vehicle> findByModelContaining (String model);

    List<Vehicle> findByEngineSizeLessThanEqual (int engineSize);

    List<Vehicle> findByEngineSizeGreaterThanEqual (int engineSize);

    List<Vehicle> findByColorContaining (String color);

    List<Vehicle> findByPowerLessThanEqual (int power);

    List<Vehicle> findByPowerGreaterThanEqual (int power);

    List<Vehicle> findByGearbox (Gearbox gearbox);

    List<Vehicle> findByRegistrationYearLessThanEqual (int registrationYear);

    List<Vehicle> findByRegistrationYearGreaterThanEqual (int registrationYear);

    List<Vehicle> findByFuelType (FuelType fuelType);

    List<Vehicle> findByPriceLessThanEqual (int price);

    List<Vehicle> findByPriceGreaterThanEqual (int price);

    List<Vehicle> findByIsDiscountedTrue ();

    List<Vehicle> findByIsNewTrue ();

    List<Vehicle> findByIsNewFalse ();

    List<Vehicle> findByVehicleState(VehicleState vehicleState);

}
