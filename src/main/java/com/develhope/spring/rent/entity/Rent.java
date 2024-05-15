package com.develhope.spring.rent.entity;

import com.develhope.spring.vehicles.entity.Vehicles;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Rent {
    @Id
    @GeneratedValue
    private long id;
    @Temporal(TemporalType.DATE)
    private Date startRent, stopRent;
    private double dailyPrice, totalPryce;
    private boolean payed;
    @ManyToOne
    @JoinColumn(name = "idVehicle", nullable = false)
    private Vehicles vehicles;
    public Rent(){}

    public long getId() {
        return id;
    }

    public Date getStartRent() {
        return startRent;
    }

    public Date getStopRent() {
        return stopRent;
    }

    public double getDailyPrice() {
        return dailyPrice;
    }

    public double getTotalPryce() {
        return totalPryce;
    }

    public boolean isPayed() {
        return payed;
    }

    public Vehicles getVehicles() {
        return vehicles;
    }
}
