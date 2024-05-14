package com.develhope.spring.vehicles.entity;

public interface Vehicles {

    void addToDealer (); // quando verrà aggiunta la classe dealer, questo metodo prenderà un'istanza di Dealer come parametro.

    enum FuelType {
        GASOLINE,
        DIESEL,
        BATTERY
    }

    enum VehicleState {
        PURCHASABLE,
        RENTABLE,
        NOT_AVAILABLE
    }
}
