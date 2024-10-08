package com.rb.auth.domain.store;

import com.rb.auth.domain.address.Address;
import com.rb.auth.domain.product.Product;
import com.rb.auth.domain.sale.Sale;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "stores")
@Entity(name = "stores")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Product> stock;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Sale> sales;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
