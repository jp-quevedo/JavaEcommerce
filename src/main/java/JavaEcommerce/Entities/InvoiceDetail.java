package JavaEcommerce.Entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class InvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer amount;

    @Column
    private Double price;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product_id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client_id;

    public InvoiceDetail() {}
    public InvoiceDetail(Integer amount, Double price, Product product_id, Client client_id) {
        this.amount = amount;
        this.price = price;
        this.product_id = product_id;
        this.client_id = client_id;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public Product getProduct_id() {
        return product_id;
    }
    public void setProduct_id(Product product_id) {
        this.product_id = product_id;
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
        InvoiceDetail that = (InvoiceDetail) o;
        return Objects.equals(id, that.id) && Objects.equals(amount, that.amount) && Objects.equals(price, that.price) && Objects.equals(product_id, that.product_id) && Objects.equals(client_id, that.client_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, price, product_id, client_id);
    }

    @Override
    public String toString() {
        return "InvoiceDetail{" +
                "id=" + id +
                ", amount=" + amount +
                ", price=" + price +
                ", product_id=" + product_id +
                ", client_id=" + client_id +
                '}';
    }

}
