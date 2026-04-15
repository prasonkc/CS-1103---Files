package com.ecommerce;

/**
 * Represents a product available for purchase
 * 
 * @author Prason KC
 */
public class Product {
    /** Generates unique product ID. */
    private static int nextId = 1;

    /** Unique identifier for this product. */
    private final int productID;
    /** Display name for this product. */
    private String name;
    /** Price of this product. */
    private double price;

    /**
     * Creates a new product with the given name and price.
     *
     * @param name  the product name; must not be null
     * @param price the product price; must be zero or positive
     * @throws IllegalArgumentException if name is null or price is negative
     */
    public Product(String name, double price) {
        this.productID = nextId++;
        setName(name);
        setPrice(price);
    }

    /**
     * Returns the product's price.
     *
     * @return the price of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Updates the product's price.
     *
     * @param price the new price; must be zero or positive
     * @throws IllegalArgumentException if price is negative
     */
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
    }

    /**
     * Returns the unique product ID.
     *
     * @return the product ID
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Returns the product's name.
     *
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the product's name.
     *
     * @param name the new name; must not be null or blank
     * @throws IllegalArgumentException if name is null or blank
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be blank.");
        }
        this.name = name;
    }

    /**
     * Returns a readable string representation of the product.
     *
     * @return formatted product details
     */
    @Override
    public String toString() {
        return "Product{id=" + productID + ", name='" + name + "', price=" + price + "}";
    }
}
