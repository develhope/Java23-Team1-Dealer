package com.develhope.spring.rent.repository;

import com.develhope.spring.rent.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository <Rent, Long> {
}
