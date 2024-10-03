package com.rb.auth.domain.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rb.auth.domain.notes.Note;
import com.rb.auth.domain.order.Order;
import com.rb.auth.domain.stock.Stock;
import com.rb.auth.domain.store.Store;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "product")
@Entity(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private int variants;

    private float retail_price;

    private float wholesale_price;

    @Column(unique = true, nullable = false)
    private String sku;

    private String category;

    private String status;

    private String gender;

    private String unit;

    private String barcode;

    @OneToMany(mappedBy = "product")
    private List<Order> orders;

    @OneToMany(mappedBy = "product")
    private List<Note> notes;

    @OneToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Product(CreateProductRequestDTO data, Stock stock) {
        this.name = data.name();
        this.description = data.description();
        this.gender = data.gender();
        this.retail_price = data.retail_price();
        this.wholesale_price = data.wholesale_price();
        this.status = data.status();
        this.barcode = data.barcode();
        this.unit = data.unit();
        this.category = data.category();
        this.sku = data.sku();
        this.variants = data.variants();
        this.stock = stock;
    }
}