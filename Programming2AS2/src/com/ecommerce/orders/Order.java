package com.ecommerce.orders;

import com.ecommerce.Customer;
import com.ecommerce.Product;

import java.util.ArrayList;

/**
 * Represents an order placed by a customer
 *
 * @author Prason KC
 */
public class Order {
    /** Generates unique order ID */
    private static int nextOrderId = 1;

    /** Unique identifier for this order. */
    private final int orderID;
    /** Customer who placed the order. */
    private final Customer customer;
    /** Products included in this order. */
    private final ArrayList<Product> products;
    /** Total price for all current products in cart */
    private double orderTotal;

    /**
     * Status values.
     */
    public enum OrderStatus {
        NEW, PAID, SHIPPED, DELIVERED, CANCELLED
    }

    /** Current status of the order. */
    private OrderStatus status;

    /**
     * Creates a new order for the given customer and products.
     *
     * @param customer the customer who placed the order; must not be null
     * @param products the products in the order; must not be null
     * @throws IllegalArgumentException if customer or products is null
     */
    public Order(Customer customer, ArrayList<Product> products) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("Products cannot be null.");
        }
        this.orderID = nextOrderId++;
        this.customer = customer;
        this.products = new ArrayList<>(products);
        this.orderTotal = calculateOrderTotal();
        this.status = OrderStatus.NEW;
    }

    private double calculateOrderTotal() {
        double total = 0;
        for (Product p : products) {
            total += p.getPrice();
        }
        return total;
    }

    /**
     * Returns the unique order ID.
     *
     * @return the order ID
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * Returns the customer who placed the order.
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Returns a copy of the product list for this order.
     *
     * @return list of products in the order
     */
    public ArrayList<Product> getProducts() {
        return new ArrayList<>(products);
    }

    /**
     * Returns the total price of the order.
     *
     * @return the order total
     */
    public double getOrderTotal() {
        return orderTotal;
    }

    /**
     * Returns the current order status.
     *
     * @return the order status
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Prints a summary of the order details.
     */
    public void generateOrderSummary() {
        System.out.println("Order: " + orderID);
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Products:");
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println("Order total: " + orderTotal);
        System.out.println("Order status: " + status);
    }

    /**
     * Updates the order status using an enum value.
     *
     * @param status the new status; must not be null
     * @throws IllegalArgumentException if status is null
     */
    public void updateOrderStatus(OrderStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Order status canot be null.");
        }
        this.status = status;
    }

    /**
     * Manages order information by updating the current status.
     *
     * @param newStatus the new status; must not be null
     * @throws IllegalArgumentException if newStatus is null
     */
    public void manageOrderInfo(OrderStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("Order status canot be null.");
        }
        this.status = newStatus;
    }
}
