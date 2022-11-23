package com.codecool.shop.manager;

import com.codecool.shop.dao.*;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ShopDatabaseManager{
    UserDao userDao;
    ProductDao productDao;
    SupplierDao supplierDao;
    ProductCategoryDao productCategoryDao;
    OrderDao orderDao;
    CartDao cartDao;


//    public DataSource connect() throws SQLException {
//        PGSimpleDataSource dataSource = new PGSimpleDataSource();
//        ApplicationProperties properties = new ApplicationProperties();
//        dataSource.setDatabaseName(properties.readProperty("cc_shop"));
//        dataSource.setUser(properties.readProperty("postgres"));
//        dataSource.setPassword(properties.readProperty("admin"));
//        System.out.println("Tying to connect");
//        dataSource.getConnection().close();
//        System.out.println("Connection ok!");
//        return dataSource;
//    }
    public enum ServerInfo {
        DB("dungen_crawl"),
        HOST("109.97.198.35"),

        PORT("5432"),

        URL("jdbc:postgresql://"),
        USER("postgres"),
        PASSWORD("admin");

        private final String info;

        ServerInfo(String info) {
            this.info = info;
        }
    }

    static final String URL = "jdbc:postgresql://109.97.198.35:5432/dungeon_crawl";
    public DataSource connect() throws SQLException {

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerName(ServerInfo.HOST.info);
        dataSource.setDatabaseName(ServerInfo.DB.info);
        dataSource.setUser(ServerInfo.USER.info);
        dataSource.setPassword(ServerInfo.PASSWORD.info);
        dataSource.setPortNumber(Integer.parseInt(ServerInfo.PORT.info));

        System.out.println("Connecting...!");
        dataSource.getConnection().close();
        System.out.println("Connecting is ready!");
        return dataSource;
    }

}
