package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private final List<LineItem> lineItems;

    public ShoppingCart(){
        lineItems = new ArrayList<>();
    }

    public List<LineItem> getItems() {
        return lineItems;
    }

    public void add(Product product){
        for(LineItem currentItem:lineItems){
            if(currentItem.getProduct().getName() == product.getName()){
                currentItem.setQuantity(currentItem.getQuantity()+1);
                currentItem.updateSubtotal();
                return;
            }
        }
        lineItems.add(new LineItem(product,1));
    }
}
