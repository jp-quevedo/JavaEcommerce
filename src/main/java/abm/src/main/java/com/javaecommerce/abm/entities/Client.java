package com.javaecommerce.abm.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "clients")
@NoArgsConstructor @ToString @EqualsAndHashCode
@Schema(description = "Represents a client")
public class Client {

    @Schema(description = "Client's identifier")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Schema(description = "Client's name", example = "John")
    @Getter @Setter private String name;

    @Schema(description = "Client's surname", example = "Doe")
    @Getter @Setter private String surname;

    @Schema(description = "Client's age", example = "27")
    @Getter @Setter private Integer age;

    @Schema(description = "Client's identification", example = "123456789")
    @Getter @Setter private Integer dni;

    @Schema(description = "Client's cart of products")
    @OneToMany(mappedBy = "client_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter
    @JsonIgnoreProperties("client_id")
    private List<Cart> cart;

    @Schema(description = "Client's purchase invoice")
    @OneToMany(mappedBy = "client_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter
    @JsonIgnoreProperties("client_id")
    private List<Invoice> invoice;

}