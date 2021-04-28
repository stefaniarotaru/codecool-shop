package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import java.util.List;

public class OrderDaoMem implements OrderDao {

    private static OrderDaoMem instance;
    private Order order = new Order();

    private OrderDaoMem() {}

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Product product){
        List<LineItem> lineItems = order.getLineItems();
        for(LineItem currentItem : lineItems) {
            if (currentItem.getProduct().getName().equals(product.getName())) {
                currentItem.setQuantity(currentItem.getQuantity() + 1);
                order.setTotal(order.getTotal() + product.getDefaultPrice());
                return;
            }
        }
        order.setTotal(order.getTotal() + product.getDefaultPrice());
        order.setCurrency(product.getDefaultCurrency().getCurrencyCode());
        lineItems.add(new LineItem(product,1));
    }

    @Override
    public Order getOrder() {
        return order;
    }

    @Override
    public void increment(int productId) {
        for(LineItem item : order.getLineItems()){
            Product product = item.getProduct();
            if(product.getId() == productId){
                item.setQuantity(item.getQuantity()+1);
                order.setTotal(order.getTotal() + product.getDefaultPrice());
                return;
            }
        }

    }

    @Override
    public void decrement(int productId) {
        for(LineItem item : order.getLineItems()){
            if(item.getProduct().getId() == productId){
                item.setQuantity(item.getQuantity()-1);
                if(item.getQuantity() == 0){
                    order.getLineItems().remove(item);
                }
                order.setTotal(order.getTotal() - item.getProduct().getDefaultPrice());

                return;
            }
        }
    }

//    @Override
//    public Order find(int id) {
//        return orders.stream()
//                .filter(order -> order.getId() == id)
//                .findFirst()
//                .orElse(null);
//    }
//
//    @Override
//    public void remove(int id) {
//        orders.remove(find(id));
//    }
//
//    @Override
//    public List<Order> getAll() {
//        return new ArrayList<>(orders);
//    }
}
