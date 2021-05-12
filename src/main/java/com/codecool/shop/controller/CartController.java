package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;

import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.*;
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

    private OrderDao orderDao = OrderDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        OrderDao orderDao = OrderDaoMem.getInstance();
        Order order = orderDao.getOrder();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("shoppingCart", order.getLineItems());

        context.setVariable("total",order.getTotal());

        System.out.println(order.getTotal());

        engine.process("cart.html", context, resp.getWriter());


        }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean increment = Boolean.parseBoolean(req.getParameter("increment"));
        int productId = Integer.parseInt(req.getParameter("productId"));

        System.out.println("FIRST PARAMETER:" +req.getParameter("increment") + "  " + "SECOND PARAMETER: " + req.getParameter("productId") );

        if(increment){
            orderDao.increment(productId);
        }else{
            orderDao.decrement(productId);
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
