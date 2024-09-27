package com.rb.auth.domain.stock;

import com.rb.auth.domain.history.ActionRecord;
import com.rb.auth.domain.product.Product;
import com.rb.auth.domain.store.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToOne (mappedBy = "stockId")
    private Store storeId;

    @OneToOne (mappedBy = "stock")
    private Product products;

    @OneToMany
    private List<ActionRecord> history;

    private int onHand;
    private int toBeReceived;
    private int toBePacked;

}
