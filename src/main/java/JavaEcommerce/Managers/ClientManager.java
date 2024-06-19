package JavaEcommerce.Managers;

import JavaEcommerce.Entities.Client;
import jakarta.persistence.EntityManager;

import java.util.Collections;
import java.util.List;

public class ClientManager {

    public void create(String name, String surname, Integer dni, Integer age) {
        try {
            EntityManager manager = GenericManager.getEntityManager();
            manager.getTransaction().begin();
            Client client = new Client(name, surname, dni, age);
            manager.persist(client);
            manager.getTransaction().commit();
            manager.close();
            System.out.println("Client created: " + client.getId());
        } catch (Exception e) {
            System.err.println("Failed to create client: " + e.getMessage());
            throw e;
        }
    }

    public List<Client> readAll() {
        try {
            EntityManager manager = GenericManager.getEntityManager();
            List<Client> list = manager.createQuery("FROM Clients", Client.class).getResultList();
            manager.close();
            return list;
        } catch (Exception e) {
            System.err.println("Failed to read clients: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Client readById(Integer id) {
        try {
            EntityManager manager = GenericManager.getEntityManager();
            Client client = manager.find(Client.class, id);
            if (client != null) {
                manager.close();
                return client;
            } else {
                manager.close();
                System.out.println("Failed to read client with the given id, please try again");
                return null;
            }
        } catch (Exception e) {
            System.err.println("Failed to read client: " + e.getMessage());
            return null;
        }
    }

    public void update(Integer id, String name, String surname, Integer dni, Integer age) {
        try {
            EntityManager manager = GenericManager.getEntityManager();
            manager.getTransaction().begin();
            Client client = manager.find(Client.class, id);
            if (client != null) {
                if (name != null) { client.setName(name); }
                if (surname != null) { client.setSurname(surname); }
                if (dni != null) { client.setDni(dni); }
                if (age != null) { client.setAge(age); }
                manager.merge(client);
                manager.getTransaction().commit();
                manager.close();
                System.out.println("Client updated: " + client.getId());
            } else {
                manager.close();
                System.out.println("Failed to update client with the given id, please try again");
            }
        } catch (Exception e) {
            System.err.println("Failed to update client: " + e.getMessage());
            throw e;
        }
    }

    public void delete(Integer id) {
        try {
            EntityManager manager = GenericManager.getEntityManager();
            manager.getTransaction().begin();
            Client client = manager.find(Client.class, id);
            if (client != null) {
                manager.remove(client);
                manager.getTransaction().commit();
                manager.close();
                System.out.println("Client deleted");
            } else {
                manager.close();
                System.out.println("Failed to delete client with the given id, please try again");
            }
        } catch (Exception e) {
            System.err.println("Failed to delete client: " + e.getMessage());
            throw e;
        }
    }

}
