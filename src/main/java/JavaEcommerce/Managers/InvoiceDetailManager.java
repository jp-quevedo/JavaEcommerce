package JavaEcommerce.Managers;

import JavaEcommerce.Entities.Client;
import JavaEcommerce.Entities.InvoiceDetail;
import JavaEcommerce.Entities.Product;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;

public class InvoiceDetailManager {

    public void addToCart(Integer amount, Double price, Product product, Client client) {
        try {
            EntityManager manager = GenericManager.getEntityManager();
            manager.getTransaction().begin();
            InvoiceDetail cart = new InvoiceDetail(amount, price, product, client);
            manager.persist(cart);
            manager.getTransaction().commit();
            manager.close();
            System.out.println("Product added to cart");
        } catch (Exception e) {
            System.err.println("Failed to add product to cart: " + e.getMessage());
            throw e;
        }
    }

    public List<InvoiceDetail> readByClientId(Client client) {
        try {
            EntityManager manager = GenericManager.getEntityManager();
            List<InvoiceDetail> list = manager
                    .createQuery("SELECT id FROM InvoiceDetail id WHERE id.client_id = :client", InvoiceDetail.class)
                    .setParameter("client", client)
                    .getResultList();
            manager.close();
            return list;
        } catch (Exception e) {
            System.err.println("Failed to read products: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void removeFromCart(Client client, Product product, Integer quantityChange) {
        try {
            EntityManager manager = GenericManager.getEntityManager();
            manager.getTransaction().begin();
            List<InvoiceDetail> list = manager
                    .createQuery("SELECT i FROM InvoiceDetail i WHERE i.product_id = :product AND i.client_id = :client", InvoiceDetail.class)
                    .setParameter("product", product)
                    .setParameter("client", client)
                    .getResultList();
            for (InvoiceDetail detail : list) {
                detail.setAmount(detail.getAmount() - quantityChange);
                if (detail.getAmount() <= 0) {
                    manager.remove(detail);
                }
            }
            manager.getTransaction().commit();
            manager.close();
            System.out.println("Product removed from cart");
        } catch (Exception e) {
            System.err.println("Failed to remove product from cart: " + e.getMessage());
            throw e;
        }
    }

}
