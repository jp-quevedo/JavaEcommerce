package JavaEcommerce.Managers;

import JavaEcommerce.Entities.Product;
import jakarta.persistence.EntityManager;

import java.util.Collections;
import java.util.List;

public class ProductManager {

    public void create(String title, Double price, Integer stock) {
        try {
            EntityManager manager = GenericManager.getEntityManager();
            manager.getTransaction().begin();
            Product product = new Product(title, price, stock);
            manager.persist(product);
            manager.getTransaction().commit();
            manager.close();
            System.out.println("Product created: " + product.getId());
        } catch (Exception e) {
            System.err.println("Failed to create product: " + e.getMessage());
            throw e;
        }
    }

    public List<Product> readAll() {
        try {
            EntityManager manager = GenericManager.getEntityManager();
            List<Product> list = manager.createQuery("FROM Products", Product.class).getResultList();
            manager.close();
            return list;
        } catch (Exception e) {
            System.err.println("Failed to read products: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Product readById(Integer id) {
        try {
            EntityManager manager = GenericManager.getEntityManager();
            Product product = manager.find(Product.class, id);
            if (product != null) {
                manager.close();
                return product;
            } else {
                manager.close();
                System.out.println("Failed to read product with the given id, please try again");
                return null;
            }
        } catch (Exception e) {
            System.err.println("Failed to read product: " + e.getMessage());
            return null;
        }
    }

    public void update(Integer id, String title, Double price, Integer stock) {
        try {
            EntityManager manager = GenericManager.getEntityManager();
            manager.getTransaction().begin();
            Product product = manager.find(Product.class, id);
            if (product!= null) {
                if (title!= null) { product.setTitle(title); }
                if (price!= null) { product.setPrice(price); }
                if (stock!= null) { product.setStock(stock); }
                manager.merge(product);
                manager.getTransaction().commit();
                manager.close();
                System.out.println("Product updated: " + product.getId());
            } else {
                manager.close();
                System.out.println("Failed to update product with the given id, please try again");
            }
        } catch (Exception e) {
            System.err.println("Failed to update product: " + e.getMessage());
            throw e;
        }
    }

    public void delete(Integer id) {
        try {
            EntityManager manager = GenericManager.getEntityManager();
            manager.getTransaction().begin();
            Product product = manager.find(Product.class, id);
            if (product!= null) {
                manager.remove(product);
                manager.getTransaction().commit();
                manager.close();
                System.out.println("Product deleted");
            } else {
                manager.close();
                System.out.println("Failed to delete product with the given id, please try again");
            }
        } catch (Exception e) {
            System.err.println("Failed to delete product: " + e.getMessage());
            throw e;
        }
    }

}
