package com.rb.auth.domain.order;

import com.rb.auth.domain.product.Product;
import com.rb.auth.domain.sale.Sale;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table (name = "orders")
@Entity  (name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private float totalPrice;

    @CreationTimestamp
    @Column(name = "created_at",nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
