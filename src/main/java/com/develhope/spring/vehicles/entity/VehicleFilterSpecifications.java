package com.develhope.spring.vehicles.entity;

import org.springframework.data.jpa.domain.Specification;

public class VehicleFilterSpecifications {

    public static Specification<Vehicle> hasVehicleKind(VehicleKind vehicleKind){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("vehicleKind"), vehicleKind));
    }

    public static Specification<Vehicle> hasBrand(String brand) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("brand"), "%" + brand + "%");
    }

    public static Specification<Vehicle> hasModel(String model) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("model"), "%" + model + "%");
    }

    public static Specification<Vehicle> hasEngineSizeBetween(int minEngineSize, int maxEngineSize) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("engineSize"), minEngineSize, maxEngineSize);
    }

    public static Specification<Vehicle> hasColor(String color) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("color"), "%" + color + "%");
    }

    public static Specification<Vehicle> hasPowerBetween(int minPower, int maxPower) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("power"), minPower, maxPower);
    }

    public static Specification<Vehicle> hasGearbox(Gearbox gearbox) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("gearbox"), gearbox);
    }

    public static Specification<Vehicle> hasRegistrationYearBetween(int minRegistrationYear, int maxRegistrationYear) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("registrationYear"), minRegistrationYear, maxRegistrationYear);
    }

    public static Specification<Vehicle> hasFuelType(FuelType fuelType) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("fuelType"), fuelType);
    }

    public static Specification<Vehicle> hasPriceBetween(double minPrice, double maxPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
    }

    public static Specification<Vehicle> isDiscounted(boolean isDiscounted) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isDiscounted"), isDiscounted);
    }

    public static Specification<Vehicle> hasDiscountBetween(double minDiscount, double maxDiscount) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("discount"), minDiscount, maxDiscount);
    }

    public static Specification<Vehicle> hasAccessories(String accessories) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("accessories"), "%" + accessories + "%");
    }

    public static Specification<Vehicle> isNew(boolean isNew) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isNew"), isNew);
    }

    public static Specification<Vehicle> hasVehicleState(VehicleState vehicleState) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("vehicleState"), vehicleState);
    }

    public static Specification<Vehicle> isPurchased(boolean purchased) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("purchased"), purchased);
    }

    public static Specification<Vehicle> isRented(boolean rented) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("rented"), rented);
    }
}
