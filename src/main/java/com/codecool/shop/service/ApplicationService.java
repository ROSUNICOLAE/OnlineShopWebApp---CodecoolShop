package com.codecool.shop.service;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementationJdbc.*;
import com.codecool.shop.manager.ShopDatabaseManager;

import javax.sql.DataSource;
import java.sql.SQLException;

public  class ApplicationService {
    private static ApplicationService instance = null;

    CartDao cartDao;
    OrderDao orderDao;
    ProductDao productDao;
    ProductCategoryDao productCategoryDao;
    SupplierDao supplierDao;
    UserDao userDao;
    DataSource dataSource;


    public ApplicationService() {
            ShopDatabaseManager shopDatabaseManager = new ShopDatabaseManager();
            try {
                this.dataSource = shopDatabaseManager.connect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            cartDao = CartDaoJdbc.getInstance();
            productCategoryDao = ProductCategoryDaoJdbc.getInstance();
            productDao = ProductDaoJdbc.getInstance();
            supplierDao = SupplierDaoJdbc.getInstance();
            orderDao = OrderDaoJdbc.getInstance();
            userDao = UserDaoJdbc.getInstance();

            ((CartDaoJdbc) cartDao).establishConnection(dataSource);
            ((OrderDaoJdbc) orderDao).establishConnection(dataSource);
            ((ProductDaoJdbc) productDao).establishConnection(dataSource);
            ((SupplierDaoJdbc) supplierDao).establishConnection(dataSource);
            ((ProductCategoryDaoJdbc) productCategoryDao).establishConnection(dataSource);
            ((UserDaoJdbc)userDao).establishConnection(dataSource);
        }



    public CartDao getCartDao() {
        return cartDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public ProductCategoryDao getProductCategoryDao() {
        return productCategoryDao;
    }

    public SupplierDao getSupplierDao() {
        return supplierDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
