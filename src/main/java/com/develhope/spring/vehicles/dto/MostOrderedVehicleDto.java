package com.develhope.spring.vehicles.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MostOrderedVehicleDto {
    private String brand;
    private String model;
}
