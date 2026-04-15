
/**
 * This program demonstrates a basic e-commerce app that focuses on online shopping functionality allowing customers to browse product catalogs, add products to their cart, and place order.
 */

import com.ecommerce.Customer;
import com.ecommerce.Product;
import com.ecommerce.orders.Order;

import java.util.ArrayList;

/**
 * Demo of a basic e-commerce app
 *
 * @author Prason KC
 */
class Main {
    /**
     * Demo of browsing products, adding items to a cart,
     * placing an order, and displaying the order summary.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        // Catalog of products available.
        ArrayList<Product> productCatalog = new ArrayList<>();
        productCatalog.add(new Product("Laptop", 499.99));
        productCatalog.add(new Product("Watch", 49.99));
        productCatalog.add(new Product("iPhone", 999.99));

        System.out.println("Available Products:");
        for (Product product : productCatalog) {
            System.out.println(product);
        }

        // Customer who is shopping
        Customer customer = new Customer("Prason KC");
        customer.addToCart(productCatalog.get(0));
        customer.addToCart(productCatalog.get(1));

        customer.removeFromCart(productCatalog.get(1));

        System.out.println();
        System.out.println("Customer: " + customer.getName());
        System.out.println("Cart Total: " + customer.calculateTotalCost());

        // Order created by the customer
        Order order = customer.placeOrder();
        System.out.println();
        order.generateOrderSummary();

        System.out.println();
        order.updateOrderStatus(Order.OrderStatus.PAID);
        order.generateOrderSummary();
    }
}
