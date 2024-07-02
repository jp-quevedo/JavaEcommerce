package com.javaecommerce.abm.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "clients")
@NoArgsConstructor @ToString @EqualsAndHashCode
public class Client {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Getter @Setter private String name;

    @Getter @Setter private String surname;

    @Getter @Setter private Integer age;

    @Getter @Setter private Integer dni;

    @OneToMany(mappedBy = "client_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter private List<Cart> cart;

    @OneToMany(mappedBy = "client_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter private List<Invoice> invoice;

}