package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {
    private final OrderDao orderDataStore = OrderDaoMem.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String orderIdParam = req.getParameter("orderId");
//        if (orderIdParam == null) {
//            resp.getWriter().println("Order ID is missing.");
//            return;
//        }
        Order order = orderDataStore.getOrder();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("orderTotal", order.getTotal() + " " + order.getCurrency());
        context.setVariable("orderId", order.getId());

        engine.process("payment/payment.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String paymentType = req.getParameter("paymentType");
        if ("card".equals(paymentType)) {
            String name = req.getParameter("name");
            String cardNumber = req.getParameter("cardNumber");

            context.setVariable("name", name);
            context.setVariable("last4Digits", cardNumber.substring(12, 16));

        }
        else if ("payPal".equals(paymentType)) {
            String username = req.getParameter("payPalUser");

            context.setVariable("username", username);
        }
        context.setVariable("paymentType", paymentType);
        engine.process("payment/confirmation.html", context, resp.getWriter());
    }
}
