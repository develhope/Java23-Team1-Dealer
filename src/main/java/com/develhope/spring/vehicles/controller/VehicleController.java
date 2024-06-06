package com.develhope.spring.vehicles.controller;

import com.develhope.spring.vehicles.entity.Vehicle;
import com.develhope.spring.vehicles.entity.VehicleKind;
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
        Vehicle vehicleAdded = vehicleService.createVehicle(vehicle);
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

    @GetMapping("/vehicleState/rentable")
    public ResponseEntity<List<Vehicle>> findRentable() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService
                        .findByVehicleState(VehicleState.RENTABLE)
                );
    }

    @GetMapping(path = "/vehicleState/purchasable")
    public ResponseEntity<List<Vehicle>> findPurchasable() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService
                        .findByVehicleState(VehicleState.PURCHASABLE)
                );
    }

    @GetMapping(path = "/vehicleState/not_available")
    public ResponseEntity<List<Vehicle>> findNotAvailable() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService
                        .findByVehicleState(VehicleState.NOT_AVAILABLE)
                );
    }

    @GetMapping("/vehicleKind/van")
    public ResponseEntity<List<Vehicle>> findVan() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByVehicleKind(VehicleKind.VAN)
                );
    }

    @GetMapping("/vehicleKind/scooter")
    public ResponseEntity<List<Vehicle>> findScooter() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByVehicleKind(VehicleKind.SCOOTER)
                );
    }

    @GetMapping("/vehicleKind/motorcycle")
    public ResponseEntity<List<Vehicle>> findMotorcycle() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByVehicleKind(VehicleKind.MOTORCYCLE)
                );
    }

    @GetMapping("/vehicleKind/car")
    public ResponseEntity<List<Vehicle>> findCar() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByVehicleKind(VehicleKind.CAR)
                );
    }

    @GetMapping("/brand")
    public ResponseEntity<List<Vehicle>> findBrandContaining (@RequestParam String brand) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByBrandContaining(brand));
    }

    @GetMapping("/model")
    public ResponseEntity<List<Vehicle>> findModelContaining (@RequestParam String model) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByModelContaining(model));
    }

    @GetMapping("/engineSize/lessThanEqual")
    public ResponseEntity<List<Vehicle>> findEngineSizeLessThanEqual (@RequestParam int engineSize) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByEngineSizeLessThanEqual(engineSize));
    }

    @GetMapping("/engineSize/greaterThanEqual")
    public ResponseEntity<List<Vehicle>> findEngineSizeGreaterThanEqual (@RequestParam int engineSize) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByEngineSizeGreaterThanEqual(engineSize));
    }

    @GetMapping("/color")
    public ResponseEntity<List<Vehicle>> findColorContaining (@RequestParam String color) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByColorContaining(color));
    }

    @GetMapping("/power/lessThanEqual")
    public ResponseEntity<List<Vehicle>> findPowerLessThanEqual (@RequestParam int power) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByPowerLessThanEqual(power));
    }

    @GetMapping("/power/greaterThanEqual")
    public ResponseEntity<List<Vehicle>> findPowerGreaterThanEqual (@RequestParam int power) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByPowerGreaterThanEqual(power));
    }

    @GetMapping("/car")
    public ResponseEntity<List<Vehicle>> findManual() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByVehicleKind(VehicleKind.CAR)
                );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicleById(id);
        return ResponseEntity.notFound().build();
    }
}
