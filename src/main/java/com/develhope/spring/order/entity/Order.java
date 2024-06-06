package com.develhope.spring.order.entity;

import com.develhope.spring.user.entity.User;
import com.develhope.spring.vehicles.entity.Vehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
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

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToOne
    private Vehicle vehicle;

}
