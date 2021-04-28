package com.codecool.shop.model;

import java.util.Objects;

public class LineItem {
    private Product product;
    private int quantity;
    private float subtotal;

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.subtotal = product.getDefaultPrice() * quantity;
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
        updateSubtotal();
    }

    public float getSubtotal() {
        return subtotal;
    }

    private void updateSubtotal(){
        subtotal = product.getDefaultPrice() * quantity;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return quantity == lineItem.quantity && Float.compare(lineItem.subtotal, subtotal) == 0 && Objects.equals(product, lineItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity, subtotal);
    }
}
