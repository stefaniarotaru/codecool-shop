package com.codecool.shop.dao;

public interface ShoppingCartDao {
    public void increment(int itemId);
    public void remove(int itemId);
    public void decrement(int itemId);
}
