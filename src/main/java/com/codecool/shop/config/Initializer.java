package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementationMem.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementationMem.ProductDaoMem;
import com.codecool.shop.dao.implementationMem.SupplierDaoMem;
import com.codecool.shop.dao.implementationMem.UserDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Role;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;
import java.util.UUID;

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
                productDataStore.add(new Product("Amazon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon, "/static/img/product_1.jpg"));
                productDataStore.add(new Product("Lenovo IdeaPad Mix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo, "/static/img/product_2.jpg"));
                productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon, "/static/img/product_3.jpg"));
                productDataStore.add(new Product("MacBook Pro 13\" Touch Bar i5 2.0GHz 1TB SSD Space Grey, layout US",new BigDecimal("2123"),"USD","\n" +
                        "Your MacBook Pro comes with apps for most everything you want to do. Edit and share your photos and videos, create presentations, and enjoy music, books, movies, and more.",laptop,apple,"https://lcdn.altex.ro/resize/media/catalog/product/M/a/2bd48d28d1c32adea0e55139a4e6434a/MacBook_Pro_13in_Silver-1.jpg"));
                productDataStore.add(new Product("iPhone 13 Pro Max 128GB Sierra Blue",new BigDecimal("1290.90"),"USD","Super Retina XDR screen with ProMotion for smooth images and quick reactions. Substantial upgrade of the camera system for spectacular new possibilities.",Smartphone,apple,"/static/img/1.jpeg"));
                productDataStore.add(new Product("iPhone 14 Plus 512GB Purple",new BigDecimal("1582"),"USD","Meet the iPhone 14 and the oversized iPhone 14 Plus. Longer battery life than ever. And even more spectacular photos in low light.\n" +
                        "Accident detection calls for help when you can't.",Smartphone,apple,"/static/img/purple.jpg"));
                productDataStore.add(new Product("iPhone 11, 64GB, Red",new BigDecimal("11187"),"USD","The iPhone 11 has a 6.1-inch IPS LCD with a resolution of 1.4 megapixels. It supports Dolby Vision, HDR10, True-Tone, and a wide color gamut.",Smartphone,apple,"/static/img/red2.jpg"));
                //creating an admin
                userDaoStore.addUser("admin", "$2a$12$IJD22UF0MSImWbTvd4OMbOsUo8B6SpqcDJ04EzlA.xM7hzoah5bW.", "admin@email.com", Role.ADMIN, UUID.randomUUID());
        }
}
