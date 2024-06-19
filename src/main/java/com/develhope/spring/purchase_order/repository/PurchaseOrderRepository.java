package com.develhope.spring.purchase_order.repository;

import com.develhope.spring.purchase_order.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends JpaRepository <PurchaseOrder, Long> {

}
