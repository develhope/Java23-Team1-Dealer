package com.develhope.spring.purchase_order.entity;

import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class PurchaseOrderSpecification {
    public static Specification<PurchaseOrder> hasOrderId(Long id) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("id"), id));
    }

    public static Specification<PurchaseOrder> hasBuyerId(Long buyerID) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("buyer").get("id"), buyerID);
    }

    public static Specification<PurchaseOrder> hasSellerId(Long sellerId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("seller").get("id"), sellerId);
    }

    public static Specification<PurchaseOrder> hasDeposit(double minDeposit, double maxDeposit) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("deposit"), minDeposit, maxDeposit);

    }

    public static Specification<PurchaseOrder> isPayed(boolean payed) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("payed"), payed);
        };
    }

    public static Specification<PurchaseOrder> createdAtBetween(Date startDate, Date endDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("createdAt"), startDate, endDate);
    }

    public static Specification<PurchaseOrder> hasStatus(PurchaseOrderStatus status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null) return criteriaBuilder.conjunction();
            return criteriaBuilder.equal(root.get("purchaseOrderStatus"), status);
        };
    }


}
