package com.develhope.spring.vehicles.controller;

import com.develhope.spring.exception.OrderNotFoundException;
import com.develhope.spring.order.entity.Order;
import com.develhope.spring.vehicles.entity.Vehicle;
import com.develhope.spring.vehicles.entity.VehicleState;
import com.develhope.spring.vehicles.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        try {
            Vehicle vehicleAdded = vehicleService.createVehicle(vehicle);
            return ResponseEntity.ok(vehicleAdded);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/shop")
    public ResponseEntity<String> shopVehicle(@RequestBody Vehicle vehicle, @RequestParam VehicleState isPurchasable) {
        try {
            boolean isPurchased = vehicleService.shopVehicle(vehicle, isPurchasable);
            if (isPurchased) {
                return ResponseEntity.ok("Vehicle has been successfully purchased!");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle purchase failed, please call our agency for more information.");
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<String> findPurchasedVehiclesByUserId(@PathVariable Long userId, Order order) {
        try {
            List<Vehicle> vehicles = vehicleService.findPurchasedVehiclesByUserId(userId, order);
            if (vehicles.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This user " + userId + " has not yet purchased vehicles with us.");
            }
            return ResponseEntity.ok(vehicles.toString());
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Order not found for user " + userId + ": " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> findByVehicleState(@RequestParam VehicleState vehicleState) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService
                        .findByVehicleState(vehicleState)
                );
    }

    @GetMapping(path = "/rentable")
    public ResponseEntity<List<Vehicle>> findRentable() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService
                        .findByVehicleState(VehicleState.RENTABLE)
                );
    }

    @GetMapping(path = "/purchasable")
    public ResponseEntity<List<Vehicle>> findPurchasable() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService
                        .findByVehicleState(VehicleState.PURCHASABLE)
                );
    }

    @GetMapping(path = "/not_available")
    public ResponseEntity<List<Vehicle>> findNotAvailable() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService
                        .findByVehicleState(VehicleState.NOT_AVAILABLE)
                );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.notFound().build();
    }
}
