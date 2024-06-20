package com.develhope.spring.purchase_order.service;

import com.develhope.spring.purchase_order.dto.PurchaseOrderMapper;
import com.develhope.spring.purchase_order.dto.PurchaseOrderResponseDTO;
import com.develhope.spring.purchase_order.entity.PurchaseOrder;
import com.develhope.spring.purchase_order.entity.PurchaseOrderStatus;
import com.develhope.spring.purchase_order.repository.PurchaseOrderRepository;
import com.develhope.spring.purchase_order.entity.PurchaseOrderSpecification;
import com.develhope.spring.user.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Data
public class PurchaseOrderFilterService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private UserRepository buyerRepository;

    private Long id;
    private Long buyerId;
    private Long sellerId;
    private Double minDeposit;
    private Double maxDeposit;
    private Boolean payed;
    private Date startDate;
    private Date endDate;
    private PurchaseOrderStatus status;


    public List<PurchaseOrder> filterPurchaseOrder() {
        Specification<PurchaseOrder> spec = Specification.where(null);

        if (id != null) {
            spec = spec.and(PurchaseOrderSpecification.hasOrderId(id));
        }
        if (buyerId != null) {
            spec = spec.and(PurchaseOrderSpecification.hasBuyerId(buyerId));
        }
        if (sellerId != null) {
            spec = spec.and(PurchaseOrderSpecification.hasSellerId(sellerId));
        }
        if (minDeposit != null && maxDeposit != null) {
            spec = spec.and(PurchaseOrderSpecification.hasDeposit(minDeposit, maxDeposit));
        }
        if (payed != null) {
            spec = spec.and(PurchaseOrderSpecification.isPayed(payed));
        }
        if (startDate != null && endDate != null) {
            spec = spec.and(PurchaseOrderSpecification.createdAtBetween(startDate, endDate));
        }
        if (status != null) {
            spec = spec.and(PurchaseOrderSpecification.hasStatus(status));
        }

        return purchaseOrderRepository.findAll(spec);
    }
}


