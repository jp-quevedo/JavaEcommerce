package JavaEcommerce.Managers;

import JavaEcommerce.Entities.Client;
import JavaEcommerce.Entities.Invoice;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;

public class InvoiceManager {

    public void create(Client client_id) {
        try {
            EntityManager manager = GenericManager.getEntityManager();
            manager.getTransaction().begin();
            LocalDateTime now = LocalDateTime.now();
            Double total = manager
                    .createQuery("SELECT SUM(d.price) FROM InvoiceDetail d WHERE d.client_id = :client_id", Double.class)
                    .setParameter("client_id", client_id)
                    .getSingleResult();
            Invoice invoice = new Invoice(total, now, client_id);
            manager.persist(invoice);
            manager.getTransaction().commit();
            manager.close();
            System.out.println("Invoice created: " + invoice.getId());
        } catch (Exception e) {
            System.err.println("Failed to create invoice: " + e.getMessage());
            throw e;
        }
    }

}