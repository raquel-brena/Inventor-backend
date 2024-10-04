package com.rb.auth.domain.sale;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rb.auth.domain.order.Order;
import com.rb.auth.domain.store.Store;
import com.rb.auth.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "sales")
@Entity(name = "sales")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<Order> orders;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @Column(nullable = false)
    private float total;

    @Column(nullable = false)
    private boolean closed;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Sale(List<Order> orders, User author, Store store, float totalWithDiscount, boolean closed) {
        this.orders = orders;
        this.author = author;
        this.store = store;
        this.total = totalWithDiscount;
        this.closed = closed;
    }
}
