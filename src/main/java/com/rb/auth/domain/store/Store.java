package com.rb.auth.domain.store;

import com.rb.auth.domain.address.Address;
import com.rb.auth.domain.product.Product;
import com.rb.auth.domain.stock.Stock;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "stores")
@Entity(name = "stores")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Store  {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @OneToOne (mappedBy = "storeId")
    private Address addressId;

    @OneToOne (mappedBy = "storeId")
    private Stock stockId;

    private Date createAt;

    public Store (CreateStoreResponseDTO store) {
        this.name = store.name();
        this.addressId = store.address();
    }
}