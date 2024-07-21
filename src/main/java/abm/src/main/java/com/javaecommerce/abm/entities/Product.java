package com.javaecommerce.abm.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor @ToString @EqualsAndHashCode
@Schema(description = "Represents a product")
public class Product {

    @Schema(description = "Product's identifier", example = "1")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Schema(description = "Product's title", example = "Guitar")
    @Getter @Setter private String title;

    @Schema(description = "Product's price", example = "1500")
    @Getter @Setter private Double price;

    @Schema(description = "Product's available stock", example = "100")
    @Getter @Setter private Integer stock;

    @Schema(description = "Selected product's ID to be referenced to the entry", example = "1")
    @OneToMany(mappedBy = "product_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter
    @JsonIgnoreProperties("client_id")
    private List<Cart> cart;

}