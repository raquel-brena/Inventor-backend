package com.rb.auth.domain.history;

import com.rb.auth.domain.product.Product;
import com.rb.auth.domain.stock.Stock;
import com.rb.auth.domain.user.User;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table (name = "action_record")
@Entity  (name = "action_record")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ActionRecord  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Stock stockId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Stock author;

    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private Date dateAction;
}
