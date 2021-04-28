package com.codecool.shop.config;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        OrderDao orderDataStore = OrderDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory laptop = new ProductCategory("Laptop", "Hardware", "A laptop is a great mobile PC.");
        productCategoryDataStore.add(laptop);
        ProductCategory smartPhone = new ProductCategory("SmartPhone", "Hardware", "A phone is a phone.");
        productCategoryDataStore.add(smartPhone);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        Product lenovoTablet = new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo);
        productDataStore.add(lenovoTablet);
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));

        productDataStore.add(new Product("Amazon laptop x999", 69.9f, "USD", "Fantastic price. Large laptop.", laptop, amazon));
        productDataStore.add(new Product("Lenovo Laptop 70010", 979, "USD", "This is a laptop for the future.", laptop, lenovo));
        productDataStore.add(new Product("Lenovo Omegawow", 1111, "USD", "Comes with 3090ti.", laptop, lenovo));
        productDataStore.add(new Product("Lenovo Budget Friendly", 665, "USD", "Comes with free keyboard.", laptop, lenovo));

        productDataStore.add(new Product("Amazon Omega SmartPhone", 99.9f, "USD", "Fantastic price. Large phone.", smartPhone, amazon));
        productDataStore.add(new Product("Lenovo End Game", 1979, "USD", "Very pricey", smartPhone, lenovo));

        List<LineItem> lineItems = new ArrayList<>();
        lineItems.add(new LineItem(lenovoTablet, 1, lenovoTablet.getDefaultPrice()));
        Order order = new Order(
                lineItems,
                lenovoTablet.getDefaultPrice(),
                new User("Stef", "whatever@blah","07662452"),
                new Address("Romania", "Bucharest", "65465", "nah"),
                new Address("Romania", "Bucharest", "65465", "nahhhh"));
        orderDataStore.add(order);
    }
}
