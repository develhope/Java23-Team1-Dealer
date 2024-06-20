package com.develhope.spring.rent_order.entity;

import com.develhope.spring.user.entity.User;
import com.develhope.spring.vehicles.entity.Vehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "rent_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentOrder {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private User user;
    @Temporal(TemporalType.DATE)
    private Date startRent, stopRent;
    private double dailyPrice, totalPrice;
    private boolean paid;
    @Enumerated(EnumType.STRING)
    private RentOrderStatus rentOrderStatus;
    @ManyToOne
    private Vehicle vehicle;
    @ManyToOne
    private User seller;

    //    public double getTotalPrice () {
//        aggiungere logica che calcola il prezzo totale in base allo startRent e stopRent.
//    }
}
