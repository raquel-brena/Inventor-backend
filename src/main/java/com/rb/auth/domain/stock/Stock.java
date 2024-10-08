package com.rb.auth.domain.stock;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rb.auth.domain.history.ActionRecord;
import com.rb.auth.domain.product.Product;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "stock")
@Entity(name = "stock")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "stock")
    private Product product;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private List<ActionRecord> history;

    private int onHand;
    private int toBeReceived;
    private int toBePacked;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}