package JavaEcommerce;

import JavaEcommerce.Managers.ClientManager;
import JavaEcommerce.Managers.InvoiceDetailManager;
import JavaEcommerce.Managers.InvoiceManager;
import JavaEcommerce.Managers.ProductManager;

public class Main {

    public static void main(String[] args) {

        ProductManager productManager = new ProductManager();
        ClientManager clientManager = new ClientManager();
        InvoiceDetailManager cartManager = new InvoiceDetailManager();
        InvoiceManager invoiceManager = new InvoiceManager();

        //productManager.create("iPhone15", 1000.5, 100);
        //productManager.create("iPhone15Pro", 1200.5, 50);
        //productManager.create("iPhone15ProMax", 1500.0, 50);
        //productManager.create("MacbookAir", 1000.0, 150);
        //productManager.create("MacbookPro", 1200.0, 150);

        //clientManager.create("JP", "Quevedo", 12345, 27);

        //cartManager.addToCart(2, productManager.readById(1).getPrice(), productManager.readById(1), clientManager.readById(1));

        //System.out.println(productManager.readById(1));
        //System.out.println(clientManager.readById(1));
        //System.out.println(cartManager.readByClientId(clientManager.readById(1)));
        //cartManager.removeFromCart(clientManager.readById(1), productManager.readById(1), 1);
        //System.out.println(cartManager.readByClientId(clientManager.readById(1)));

        invoiceManager.create(clientManager.readById(1));

    }

}
