package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "productController",urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    ProductDao productDataStore = ProductDaoMem.getInstance();
    ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
    OrderDao orderDao = OrderDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        int sortParameter = 0;
        if(req.getParameter("sortParameter") !=null){
            sortParameter = Integer.parseInt(req.getParameter("sortParameter"));
        }


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        if(sortParameter == 0){
            context.setVariable("categories", productCategoryDataStore.getAll());
            context.setVariable("products", productDataStore);
        }

        else if(sortParameter == 1){
            List<ProductCategory> categories;
            categories = productCategoryDataStore.getAll();

            Collections.sort(categories, new Comparator<ProductCategory>() {
                @Override
                public int compare(ProductCategory o1, ProductCategory o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            context.setVariable("categories", categories);
            context.setVariable("products", productDataStore);
        }

        else if(sortParameter == 2){
            List<Supplier> categories;
            categories = supplierDataStore.getAll();

            Collections.sort(categories, new Comparator<Supplier>() {
                @Override
                public int compare(Supplier o1, Supplier o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            context.setVariable("categories", categories);
            context.setVariable("products", productDataStore);


        }



//        context.setVariable("category", productService.getProductCategory(1));
//        context.setVariable("products", productService.getProductsForCategory(1));


        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);

        engine.process("product/index.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int productId = Integer.parseInt(req.getParameter("productId"));
        Product queriedProduct = productDataStore.find(productId);

        orderDao.add(queriedProduct);

        doGet(req,resp);

    }


}
