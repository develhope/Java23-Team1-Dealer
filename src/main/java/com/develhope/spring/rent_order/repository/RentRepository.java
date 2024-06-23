package com.develhope.spring.rent_order.repository;

import com.develhope.spring.rent_order.entity.RentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository <RentOrder, Long> {
}
