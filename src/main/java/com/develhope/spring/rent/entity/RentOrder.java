package com.develhope.spring.rent.entity;

import com.develhope.spring.user.entity.User;
import com.develhope.spring.vehicles.entity.Vehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "rent")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentOrder {
    @Id
    @GeneratedValue
    private long id;
    @Temporal(TemporalType.DATE)
    private Date startRent, stopRent;
    private double dailyPrice, totalPrice;
    private boolean payed;
    @Enumerated(EnumType.STRING)
    private RentOrderStatus rentOrderStatus;
    @ManyToOne
    private Vehicle vehicle;
    @ManyToOne
    private User seller;
}
