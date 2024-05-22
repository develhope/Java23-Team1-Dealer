package com.develhope.spring.rent.entity;

import com.develhope.spring.vehicles.entity.Vehicle;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table
@Data
@NoArgsConstructor
public class Rent {
    @Id
    @GeneratedValue
    private long id;
    @Temporal(TemporalType.DATE)
    private Date startRent, stopRent;
    private double dailyPrice, totalPryce;
    private boolean payed;
    @ManyToOne
    private Vehicle vehicles;
}
