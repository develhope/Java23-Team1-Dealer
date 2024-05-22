package com.develhope.spring.order.entity;

import com.develhope.spring.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`order`")
@Data
@NoArgsConstructor
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

}
