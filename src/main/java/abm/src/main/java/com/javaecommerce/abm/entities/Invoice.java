package com.javaecommerce.abm.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "invoices")
@NoArgsConstructor @ToString @EqualsAndHashCode
@Schema(description = "Represents an invoice")
public class Invoice {

    @Schema(description = "Invoice's identifier", example = "1")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Schema(description = "Invoice's total price", example = "1550")
    @Getter @Setter private Double total;

    @Schema(description = "Invoice's date of generation", example = "2007-12-03T10:15:30:55.000000")
    @Getter @Setter private LocalDateTime created_at;

    @Schema(description = "Selected client's ID to be referenced to the invoice", example = "1")
    @ManyToOne @JoinColumn(name = "client_id")
    @Getter @Setter
    @JsonIgnoreProperties("invoice")
    private Client client_id;

}