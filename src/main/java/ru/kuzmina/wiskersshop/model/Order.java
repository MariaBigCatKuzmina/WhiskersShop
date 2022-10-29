package ru.kuzmina.wiskersshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "sum")
    private Double orderPrice;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Order(LocalDateTime orderDate, Double orderPrice, User user) {
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
        this.user = user;
    }
}
