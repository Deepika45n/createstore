package com.deecodes.createstore;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name ="price_At_purchase",nullable = false)
    private BigDecimal price_At_purchase;
    @ManyToOne
    @JoinColumn( name = "order_id", nullable = false)
    private Order order;

    @JoinColumn( name = "product_id", nullable = false)
    @ManyToOne
    private Product product;

}
