package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private List<LineItem> lineItems;
    private float total;
    private String currency;
    private User user;
    private Address shippingAddress;
    private Address billingAddress;

    public Order() {
        this.lineItems = new ArrayList<>();
    }

    public Order(List<LineItem> lineItems, float total, String currency, User user, Address shippingAddress, Address billingAddress) {
        this.lineItems = lineItems;
        this.total = total;
        this.currency = currency;
        this.user = user;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < lineItems.size(); i++) {
            sb.append(lineItems.get(i).toString());
            if (i != lineItems.size() - 1) {
                sb.append("; ");
            }
        }
        return String.format("id: %1$d," +
                        "lineItems: %2$s," +
                        "total: %3$f," +
                        "currency: %4$s," +
                        "user: %5$s," +
                        "shippingAddress: %6$s," +
                        "billingAddress: %7$s",
                this.id,
                sb.toString(),
                this.total,
                this.currency,
                this.user,
                this.shippingAddress,
                this.billingAddress);

    }
}
