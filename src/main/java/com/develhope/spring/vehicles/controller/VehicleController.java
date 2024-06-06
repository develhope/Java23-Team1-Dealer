package com.develhope.spring.vehicles.controller;

import com.develhope.spring.vehicles.entity.*;

import com.develhope.spring.vehicles.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<List<Vehicle>> findVehicleState_Rentable() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService
                        .findByVehicleState(VehicleState.RENTABLE)
                );
    }

    @GetMapping(path = "/vehicleState/purchasable")
    public ResponseEntity<List<Vehicle>> findVehicleState_Purchasable() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService
                        .findByVehicleState(VehicleState.PURCHASABLE)
                );
    }

    @GetMapping(path = "/vehicleState/not_available")
    public ResponseEntity<List<Vehicle>> findVehicleState_NotAvailable() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService
                        .findByVehicleState(VehicleState.NOT_AVAILABLE)
                );
    }

    @GetMapping("/vehicleKind/van")
    public ResponseEntity<List<Vehicle>> findVehicleKind_Van() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByVehicleKind(VehicleKind.VAN)
                );
    }

    @GetMapping("/vehicleKind/scooter")
    public ResponseEntity<List<Vehicle>> findVehicleKind_Scooter() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByVehicleKind(VehicleKind.SCOOTER)
                );
    }

    @GetMapping("/vehicleKind/motorcycle")
    public ResponseEntity<List<Vehicle>> findVehicleKind_Motorcycle() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByVehicleKind(VehicleKind.MOTORCYCLE)
                );
    }

    @GetMapping("/vehicleKind/car")
    public ResponseEntity<List<Vehicle>> findVehicleKind_Car() {
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

    @GetMapping("/gearbox/manual")
    public ResponseEntity<List<Vehicle>> findGearbox_Manual() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByGearbox(Gearbox.MANUAL)
                );
    }

    @GetMapping("/gearbox/semi")
    public ResponseEntity<List<Vehicle>> findGearbox_Semi() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByGearbox(Gearbox.SEMI)
                );
    }

    @GetMapping("/gearbox/automatic")
    public ResponseEntity<List<Vehicle>> findGearbox_Automatic() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByGearbox(Gearbox.AUTOMATIC)
                );
    }

    @GetMapping("/registrationYear/lessThanEqual")
    public ResponseEntity<List<Vehicle>> findRegistrationYearLessThanEqual (@RequestParam int registrationYear) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByRegistrationYearLessThanEqual(registrationYear));
    }

    @GetMapping("/registrationYear/greaterThanEqual")
    public ResponseEntity<List<Vehicle>> findRegistrationYearGreaterThanEqual (@RequestParam int registrationYear) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByRegistrationYearGreaterThanEqual(registrationYear));
    }

    @GetMapping("/fuelType/gasoline")
    public ResponseEntity<List<Vehicle>> findFuelType_Gasoline() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByFuelType(FuelType.GASOLINE)
                );
    }

    @GetMapping("/fuelType/diesel")
    public ResponseEntity<List<Vehicle>> findFuelType_Diesel() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByFuelType(FuelType.DIESEL)
                );
    }

    @GetMapping("/fuelType/battery")
    public ResponseEntity<List<Vehicle>> findFuelType_Battery() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByFuelType(FuelType.BATTERY)
                );
    }

    @GetMapping("/price/lessThanEqual")
    public ResponseEntity<List<Vehicle>> findPriceLessThanEqual (@RequestParam int price) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByPriceLessThanEqual(price));
    }

    @GetMapping("/price/greaterThanEqual")
    public ResponseEntity<List<Vehicle>> findPriceGreaterThanEqual (@RequestParam int price) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByPriceGreaterThanEqual(price));
    }

    @GetMapping("/isDiscounted/true")
    public ResponseEntity<List<Vehicle>> findIsDiscountedTrue () {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByIsDiscountedTrue());
    }

    @GetMapping("/isNew/true")
    public ResponseEntity<List<Vehicle>> findIsNewTrue () {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByIsNewTrue());
    }

    @GetMapping("/isNew/false")
    public ResponseEntity<List<Vehicle>> findIsNewFalse () {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService.findByIsNewFalse());
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(vehicleService
                        .findAll()
                );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicleById(id);
        return ResponseEntity.notFound().build();
    }
}
