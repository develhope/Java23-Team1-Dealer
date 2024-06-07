package com.develhope.spring.vehicles.filter;

import com.develhope.spring.vehicles.entity.Vehicle;
import com.develhope.spring.vehicles.entity.VehicleKind;
import com.develhope.spring.vehicles.entity.FuelType;
import com.develhope.spring.vehicles.entity.Gearbox;
import com.develhope.spring.vehicles.entity.VehicleState;
import org.springframework.data.jpa.domain.Specification;

public class VehicleSpecifications {

    public static Specification<Vehicle> hasBrand(String brand) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("brand"), brand);
    }

    public static Specification<Vehicle> hasModel(String model) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("model"), model);
    }

    public static Specification<Vehicle> hasVehicleKind(VehicleKind vehicleKind) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("vehicleKind"), vehicleKind);
    }

    public static Specification<Vehicle> hasFuelType(FuelType fuelType) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("fuelType"), fuelType);
    }

    public static Specification<Vehicle> hasGearbox(Gearbox gearbox) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("gearbox"), gearbox);
    }

    public static Specification<Vehicle> hasVehicleState(VehicleState vehicleState) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("vehicleState"), vehicleState);
    }
}
