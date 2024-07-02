package com.javaecommerce.abm.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor @ToString @EqualsAndHashCode
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Getter @Setter private String title;

    @Getter @Setter private Double price;

    @Getter @Setter private Integer stock;

    @OneToMany(mappedBy = "product_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter private List<Cart> cart;
}
