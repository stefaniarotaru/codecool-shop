package com.codecool.shop.dao;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

public interface OrderDao {
    void add(Product product);
    Order getOrder();
    void increment(int productId);
    void decrement(int productId);
//    Order find(int id);
//    void remove(int id);
//    List<Order> getAll();
}
