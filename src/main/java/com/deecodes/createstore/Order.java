package com.deecodes.createstore;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "customer namr is required")
    @Column(name="customer_name",nullable=false)
    private  String customerName;

    @Email(message = "Invalid email format")
    @NotNull(message = "customer email is required")
    @Column(name="customer_email",nullable=false,unique=true)
    private String customerEmail;

    public enum OrderStatus { PENDING, PROCESSING, COMPLETED, CANCELLED }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(name = "total_price",nullable = false)
    @DecimalMin(value = "0.0", inclusive = false , message = "Price should be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Invalid price format")
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @Column(name = "created_at")
    private LocalDate created_at;

    @PrePersist
    void prePersist(){
        this.created_at=LocalDate.now();

    }


}
