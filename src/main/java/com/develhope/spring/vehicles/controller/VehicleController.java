package com.develhope.spring.vehicles.controller;
import com.develhope.spring.vehicles.entity.Vehicle;
import com.develhope.spring.vehicles.entity.VehicleState;
import com.develhope.spring.vehicles.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    
    @Autowired
    private VehicleService vehicleServices;

    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        Vehicle vehicleAdded = vehicleServices.insertVehicle(vehicle);
        return ResponseEntity.ok(vehicleAdded);
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
        vehicleServices.deleteVehicle(id);
        return ResponseEntity.notFound().build();
    }

}
