package com.develhope.spring.purchase_order.entity;

import com.develhope.spring.user.entity.User;
import com.develhope.spring.vehicles.entity.Vehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "`purchase_order`")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private User buyer;
    private double deposit;
    private boolean paid;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private PurchaseOrderStatus purchaseOrderStatus;
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    @ManyToOne
    private User seller;

}
