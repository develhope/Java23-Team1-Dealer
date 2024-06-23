package com.develhope.spring.purchase_order.repository;

import com.develhope.spring.purchase_order.dto.OrderCountDTO;
import com.develhope.spring.purchase_order.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    @Query("SELECT new com.develhope.spring.purchase_order.dto.OrderCountDTO(v.brand, v.model, COUNT(po.id)) " +
            "FROM PurchaseOrder po JOIN po.vehicle v " +
            "WHERE po.createdAt BETWEEN :startDate AND :endDate " +
            "GROUP BY v.brand, v.model")
    List<OrderCountDTO> findOrderCountByBrandAndModel(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
