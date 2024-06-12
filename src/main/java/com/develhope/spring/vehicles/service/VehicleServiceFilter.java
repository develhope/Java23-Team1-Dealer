package com.develhope.spring.vehicles.service;

import com.develhope.spring.exception.customException.VehicleNotFoundException;
import com.develhope.spring.vehicles.entity.*;
import com.develhope.spring.vehicles.entity.VehicleFilterSpecifications;
import com.develhope.spring.vehicles.repository.VehicleRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@Data
@NoArgsConstructor
public class VehicleServiceFilter {

    private VehicleRepository vehicleRepository;

    private VehicleKind vehicleKind;
    private String brand;
    private String model;
    private Integer minEngineSize;
    private Integer maxEngineSize;
    private String color;
    private Integer minPower;
    private Integer maxPower;
    private Gearbox gearbox;
    private Integer minRegistrationYear;
    private Integer maxRegistrationYear;
    private FuelType fuelType;
    private Integer minPrice;
    private Integer maxPrice;
    private Boolean discounted;
    private Double minDiscount;
    private Double maxDiscount;
    private String accessories;
    private Boolean isNew;
    private VehicleState vehicleState;

    public List<Vehicle> getFilteredVehicles(
    ) {
        Specification<Vehicle> specification = Specification.where(null);

        if (vehicleKind != null) {
            specification = specification.and(VehicleFilterSpecifications.hasVehicleKind(vehicleKind));
        }

        if (brand != null && !brand.isEmpty()) {
            specification = specification.and(VehicleFilterSpecifications.hasBrand(brand));
        }

        if (model != null && !model.isEmpty()) {
            specification = specification.and(VehicleFilterSpecifications.hasModel(model));
        }

        if (minEngineSize != null || maxEngineSize != null) {
            if (minEngineSize == null) minEngineSize = 0;
            if (maxEngineSize == null) maxEngineSize = Integer.MAX_VALUE;
            specification = specification.and(VehicleFilterSpecifications.hasEngineSizeBetween(minEngineSize, maxEngineSize));
        }

        if (color != null && !color.isEmpty()) {
            specification = specification.and(VehicleFilterSpecifications.hasColor(color));
        }

        if (minPower != null || maxPower != null) {
            if (minPower == null) minPower = 0;
            if (maxPower == null) maxPower = Integer.MAX_VALUE;
            specification = specification.and(VehicleFilterSpecifications.hasPowerBetween(minPower, maxPower));
        }

        if (minRegistrationYear != null || maxRegistrationYear != null) {
            if (minRegistrationYear == null) minRegistrationYear = 0;
            if (maxRegistrationYear == null) maxRegistrationYear = Integer.MAX_VALUE;
            specification = specification.and(VehicleFilterSpecifications.hasRegistrationYearBetween(minRegistrationYear, maxRegistrationYear));
        }

        if (gearbox != null) {
            specification = specification.and(VehicleFilterSpecifications.hasGearbox(gearbox));
        }


        if (fuelType != null) {
            specification = specification.and(VehicleFilterSpecifications.hasFuelType(fuelType));
        }

        if (minPrice != null || maxPrice != null) {
            if (minPrice == null) minPrice = 0;
            if (maxPrice == null) maxPrice = Integer.MAX_VALUE;
            specification = specification.and(VehicleFilterSpecifications.hasPriceBetween(minPrice, maxPrice));
        }

        if (discounted != null) {
            specification = specification.and(VehicleFilterSpecifications.isDiscounted(discounted));
        }

        if (minDiscount != null || maxDiscount != null) {
            if (minDiscount == null) minPrice = 0;
            if (maxDiscount == null) maxPrice = Integer.MAX_VALUE;
            specification = specification.and(VehicleFilterSpecifications.hasDiscountBetween(minDiscount, maxDiscount));
        }

        if (accessories != null && !accessories.isEmpty()) {
            specification = specification.and(VehicleFilterSpecifications.hasAccessories(accessories));
        }

        if (isNew != null) {
            specification = specification.and(VehicleFilterSpecifications.isNew(isNew));
        }

        if (vehicleState != null) {
            specification = specification.and(VehicleFilterSpecifications.hasVehicleState(vehicleState));
        }

        List<Vehicle> results = vehicleRepository.findAll(specification);

        if (results.isEmpty()) throw new VehicleNotFoundException("No vehicles with this criteria found");

        return results;
    }
}
