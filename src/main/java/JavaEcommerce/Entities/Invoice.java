package JavaEcommerce.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Double total;

    @Column
    private LocalDateTime created_at;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client_id;

    public Invoice() {}
    public Invoice(Double total, LocalDateTime created_at, Client client_id) {
        this.total = total;
        this.created_at = created_at;
        this.client_id = client_id;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }
    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public Client getClient_id() {
        return client_id;
    }
    public void setClient_id(Client client_id) {
        this.client_id = client_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id) && Objects.equals(total, invoice.total) && Objects.equals(created_at, invoice.created_at) && Objects.equals(client_id, invoice.client_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, total, created_at, client_id);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", total=" + total +
                ", created_at=" + created_at +
                ", client_id=" + client_id +
                '}';
    }

}
