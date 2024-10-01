package com.rb.auth.domain.history;

import com.rb.auth.domain.product.Product;
import com.rb.auth.domain.stock.Stock;
import com.rb.auth.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "action_record")
@Entity(name = "action_record")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ActionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private Date dateAction;
}
