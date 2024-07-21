package com.javaecommerce.abm.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "carts")
@NoArgsConstructor @ToString @EqualsAndHashCode
@Schema(description = "Represents a cart")
public class Cart {

    @Schema(description = "Cart entry's identifier", example = "1")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Schema(description = "Selected product's quantity to be added to cart", example = "1")
    @Getter @Setter private Integer amount;

    @Schema(description = "Selected product's price to be added to cart", example = "1500.00")
    @Getter @Setter private Double price;

    @Schema(description = "Selected product's delivery status to be added to cart", example = "false")
    @Getter @Setter private Boolean delivered = false;

    @Schema(description = "Selected product's ID to be added to cart", example = "1")
    @ManyToOne @JoinColumn(name = "product_id")
    @Getter @Setter
    @JsonIgnoreProperties("cart")
    private Product product_id;

    @Schema(description = "Selected client's ID to be referenced to the entry", example = "1")
    @ManyToOne @JoinColumn(name = "client_id")
    @Getter @Setter
    @JsonIgnoreProperties("cart")
    private Client client_id;

}