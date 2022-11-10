package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Role;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        UserDao userDaoStore= UserDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo");
        supplierDataStore.add(lenovo);
        Supplier apple = new Supplier("Apple");
        supplierDataStore.add(apple);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory laptop = new ProductCategory("Laptop","Hardware","Portable power");
        productCategoryDataStore.add(laptop);
        ProductCategory Smartphone = new ProductCategory("smartphone","Hardware","Smart at your fingers");
        productCategoryDataStore.add(Smartphone);
        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", new BigDecimal("49.9"), "EUR", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon, "/static/img/product_1.jpg"));
        productDataStore.add(new Product("Lenovo IdeaPad Mix 700", new BigDecimal("479"), "EUR", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo, "/static/img/product_2.jpg"));
        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "EUR", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon, "/static/img/product_3.jpg"));
        productDataStore.add(new Product("MacBook Noob",new BigDecimal("1200"),"EUR","test description",laptop,amazon,"https://lcdn.altex.ro/resize/media/catalog/product/M/a/2bd48d28d1c32adea0e55139a4e6434a/MacBook_Pro_13in_Silver-1.jpg"));
        productDataStore.add(new Product("iPhone 13 Pro Max 128GB Sierra Blue",new BigDecimal("1290"),"EUR","Super Retina XDR screen with ProMotion for smooth images and quick reactions. Substantial upgrade of the camera system for spectacular new possibilities. Exceptional durability. Ultra fast A15 Bionic chip.",Smartphone,apple,"/static/img/1.jpeg"));
        productDataStore.add(new Product("Phone2",new BigDecimal("1001"),"EUR","Smarphone",Smartphone,apple,"/static/img/2.jpeg"));
        productDataStore.add(new Product("iPhone 11, 64GB, Red",new BigDecimal("11187"),"EUR","The iPhone 11 has a 6.1-inch IPS LCD with a resolution of 1.4 megapixels. It supports Dolby Vision, HDR10, True-Tone, and a wide color gamut.",Smartphone,apple,"/static/img/3.png"));
        //creating an admin
        userDaoStore.addUser("admin", "admin", "admin@email.com", Role.ADMIN);
    }
}
