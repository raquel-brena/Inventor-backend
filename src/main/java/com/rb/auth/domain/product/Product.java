package com.rb.auth.domain.product;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(unique = true, nullable = false)
    private String sku;

    @Column(nullable = false)
    private Integer price;

    private Category category;

    @Column(nullable = false)
    private Integer quantity;

    public Product(ProductRequestDTO data){
        this.price = data.price();
        this.name = data.name();
        this.description = data.description();
        this.category = data.category();
        this.sku = data.sku();
        this.quantity = data.quantity();
    }
}