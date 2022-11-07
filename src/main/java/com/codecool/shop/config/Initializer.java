package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
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

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Laptop");
        supplierDataStore.add(lenovo);
        Supplier apple = new Supplier("Apple", "Phones");
        supplierDataStore.add(apple);
        Supplier hp = new Supplier("HP", "Laptop");
        supplierDataStore.add(hp);
        Supplier samsung = new Supplier("Samsung", "Tablet");
        supplierDataStore.add(samsung);


        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware",
                "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a " +
                        "touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory phone = new ProductCategory("Phones", "", "Smart phones");
        productCategoryDataStore.add(phone);
        ProductCategory laptop = new ProductCategory("Laptop", "", "Laptop");
        productCategoryDataStore.add(laptop);

        //setting up products and printing it
        productDataStore.add(new Product("Telefon mobil Apple iPhone 11, 64GB, Red", new BigDecimal("2"), "EUR", "New system with two cameras. Battery for the whole day. The most durable glass ever used in a smartphone. Apple's fastest processor ever.", phone, apple));
        productDataStore.add(new Product("Tableta SAMSUNG Galaxy Tab S8", new BigDecimal("5"), "EUR", "Laptop HP Pavilion Aero 13-be0031nq, AMD Ryzen 5, 13.3\"," +
                " Tableta SAMSUNG Galaxy Tab S8, 11\", 128GB, 8GB RAM, Wi-Fi, Gray", tablet, samsung));
        productDataStore.add(new Product("Laptop HP Pavilion Aero 13", new BigDecimal("24"), "EUR", "Laptop HP Pavilion Aero 13-be0031nq, AMD Ryzen 5, 13.3\"," +
                " WUXGA IPS, 16GB, SSD 512GB, AMD Radeon Graphics, Windows 11 Home, Pale Rose Gold", laptop, hp));

    }
}
