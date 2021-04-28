package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoImpl;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.ProductService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "cartController",urlPatterns = {"/cart.html"})
public class CartController extends HttpServlet {

    ProductDao productDataStore = ProductDaoMem.getInstance();
    ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    ShoppingCartDaoImpl shoppingCartDao =  ShoppingCartDaoImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ShoppingCart shoppingCart = ShoppingCartDaoImpl.getShoppingCart();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("shoppingCart", shoppingCart.getItems());

        float total =0;

        for(int i = 0;i < shoppingCartDao.getShoppingCart().getItems().size();i++){
            total = total + shoppingCartDao.getShoppingCart().getItems().get(i).getSubtotal();
        }

        context.setVariable("total",total);

        System.out.println(total);

        engine.process("product/cart.html", context, resp.getWriter());


        }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean increment = Boolean.parseBoolean(req.getParameter("increment"));
        int productId = Integer.parseInt(req.getParameter("productId"));

        System.out.println("FIRST PARAMETER:" +req.getParameter("increment") + "  " + "SECOND PARAMETER: " + req.getParameter("productId") );

        if(increment){
            shoppingCartDao.increment(productId);
        }else{
            shoppingCartDao.decrement(productId);
        }

        for (LineItem item:shoppingCartDao.getShoppingCart().getItems()){
            if(item.getProduct().getId() == productId){
                item.updateSubtotal();
                break;
            }
        }


//        System.out.println("\n\n");
//        for(int i = 0;i < shoppingCartDao.getShoppingCart().getItems().size();i++){
//            System.out.println(shoppingCartDao.getShoppingCart().getItems().get(i).getProduct().getName()
//                    + " " + shoppingCartDao.getShoppingCart().getItems().get(i).getQuantity()
//            );
//        }

        doGet(req,resp);

    }


}
