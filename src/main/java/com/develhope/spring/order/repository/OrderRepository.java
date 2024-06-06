package com.develhope.spring.order.repository;

import com.develhope.spring.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository <Order, Long> {
    boolean existsByUserId(long id);
    Optional<Order> findOrderByUserId(Long id);
}

