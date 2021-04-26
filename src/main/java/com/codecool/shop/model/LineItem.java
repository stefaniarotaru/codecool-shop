package com.codecool.shop.model;

public class LineItem {
    private Product product;
    private int quantity;
    private float subtotal;

    public LineItem(Product product, int quantity, float subtotal) {
        this.product = product;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return String.format("product: %1$s," +
                "quantity: %2$d," +
                "subtotal: %3$f",
                this.product,
                this.quantity,
                this.subtotal);

    }
}
