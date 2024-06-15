package com.develhope.spring.order.entity;

import com.develhope.spring.user.entity.User;
import com.develhope.spring.vehicles.entity.Vehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.TimeZone;

@Entity
@Table(name = "`order`")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private User user;
    private double deposit;
    private boolean payed;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @OneToOne
    private Vehicle vehicle;
    @ManyToOne
    private User seller;
}
