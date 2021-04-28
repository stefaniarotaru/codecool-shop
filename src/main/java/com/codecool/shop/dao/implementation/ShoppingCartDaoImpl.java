package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.ShoppingCart;

public class ShoppingCartDaoImpl implements ShoppingCartDao {

    private static ShoppingCart shoppingCart;
    private static ShoppingCartDaoImpl instance = null;


    private ShoppingCartDaoImpl(){
        shoppingCart = new ShoppingCart();
    }

    public static ShoppingCartDaoImpl getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoImpl();
        }
        return instance;
    }


    public static ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public void increment(int itemId) {

        for(LineItem item:shoppingCart.getItems()){
            if(item.getProduct().getId() == itemId){
                item.setQuantity(item.getQuantity()+1);
                return;
            }
        }

    }

    @Override
    public void remove(int itemId) {
        for(LineItem item:shoppingCart.getItems()){
            if(item.getProduct().getId() == itemId){

                return;
            }
        }
    }

    @Override
    public void decrement(int itemId) {
        for(LineItem item:shoppingCart.getItems()){
            if(item.getProduct().getId() == itemId){
                item.setQuantity(item.getQuantity()-1);
                if(item.getQuantity() ==0){
                    shoppingCart.getItems().remove(item);
                }
                return;
            }
        }
    }

}
