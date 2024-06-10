package com.develhope.spring.order.repository;

import com.develhope.spring.order.entity.Order;
import com.develhope.spring.vehicles.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository <Order, Long> {
    List<Order> findByUserId(Long userId);
}
