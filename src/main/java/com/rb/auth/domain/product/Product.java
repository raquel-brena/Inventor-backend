package com.rb.auth.domain.product;

import java.util.List;

import com.rb.auth.domain.enums.Category;
import com.rb.auth.domain.enums.Gender;
import com.rb.auth.domain.enums.Status;
import com.rb.auth.domain.enums.Unit;

import com.rb.auth.domain.history.ActionRecord;
import com.rb.auth.domain.notes.Note;
import com.rb.auth.domain.stock.Stock;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

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

    @Column (nullable = false, columnDefinition = "boolean default true")
    private boolean active;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private int variants;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private float retail_price;

    private float wholesale_price;

    @Column(unique = true, nullable = false)
    private String sku;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    private String barcode;

    @Enumerated(EnumType.STRING)
    private Unit unit;
    
    @OneToMany(mappedBy = "product")
    private List<ActionRecord> history;

    @OneToMany (mappedBy = "product")
    private List<Note> notes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name="stock_id", referencedColumnName = "id")
    private Stock stock;

    public Product(CreateProductRequestDTO data){
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
        this.stock = data.stock();
    }
}