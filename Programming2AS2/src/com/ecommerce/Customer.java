package com.ecommerce;

import com.ecommerce.orders.Order;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a customer with a shopping cart
 *
 * @author Prason KC
 */
public class Customer {
    /** Generates unique customer ID. */
    private static int nextCustomerId = 1;
    /** Unique identifier for this customer. */
    private final int customerID;
    /** Customer name. */
    private String name;

    /** Current shopping cart for the customer. */
    private final ArrayList<Product> shoppingCart;

    /**
     * Creates a new customer with the given name.
     *
     * @param name the customer name; must not be null
     * @throws IllegalArgumentException if name is null
     */
    public Customer(String name) {
        this.customerID = nextCustomerId++;
        setName(name);
        shoppingCart = new ArrayList<Product>();
    }

    /**
     * Returns the unique customer ID.
     *
     * @return the customer ID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Returns the customer's name.
     *
     * @return the customer name
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the customer's name.
     *
     * @param name the new name; must not be null
     * @throws IllegalArgumentException if name is null
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be blank");
        }
        this.name = name;
    }

    /**
     * Returns the customer's current shopping cart.
     *
     * @return the shopping cart list
     */
    public ArrayList<Product> getShoppingCart() {
        return shoppingCart;
    }

    /**
     * Adds a product to the shopping cart.
     *
     * @param product the product to add; must not be null
     * @throws IllegalArgumentException if product is null
     */
    public void addToCart(Product product) {
        if (product != null) {
            shoppingCart.add(product);
        } else {
            throw new IllegalArgumentException("Product is Null");
        }
    }

    /**
     * Removes a product from the shopping cart if present.
     *
     * @param product the product to remove; must not be null
     */
    public void removeFromCart(Product product) {
        try {
            shoppingCart.removeIf(p -> Objects.equals(p, product));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Calculates the total cost of the cart.
     *
     * @return total price of all products in the cart
     */
    public double calculateTotalCost() {
        double totalCost = 0;
        for (Product p : shoppingCart) {
            totalCost += p.getPrice();
        }
        return totalCost;
    }

    /**
     * Places an order with the current cart contents.
     *
     * @return the newly created order
     * @throws IllegalArgumentException if the cart is empty
     */
    public Order placeOrder() {
        if (shoppingCart.isEmpty()) {
            throw new IllegalArgumentException("Cannot place order on empty cart");
        }
        ArrayList<Product> finalProducts = new ArrayList<>(shoppingCart);
        Order newOrder = new Order(this, finalProducts);
        shoppingCart.clear();
        return newOrder;
    }
}
