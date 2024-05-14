package com.develhope.spring.rent.repository;

import com.develhope.spring.rent.entity.Rent;
import com.develhope.spring.vehicles.entity.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository <Rent, Long> {
}
