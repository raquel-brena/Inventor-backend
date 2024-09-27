package com.rb.auth.domain.address;

import com.rb.auth.domain.store.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "address")
@Entity(name = "address")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(mappedBy = "address")
    private Store store;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String streetName;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private Integer number;

    public Address(String state, String city, String s, String neighborhood, Integer number) {
    }
}
