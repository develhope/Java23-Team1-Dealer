package com.develhope.spring.order.repository;

import com.develhope.spring.order.entity.Order;
import com.develhope.spring.vehicles.entity.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository <Order, Long> {
}
