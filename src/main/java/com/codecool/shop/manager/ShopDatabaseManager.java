package com.codecool.shop.manager;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ShopDatabaseManager{

    public enum ServerInfo {
        DB("cc_shop"),
        HOST("109.97.198.79"),

        PORT("5432"),

        URL("jdbc:postgresql://"),
        USER("postgres"),
        PASSWORD("admin");

        private final String info;

        ServerInfo(String info) {
            this.info = info;
        }
    }

    static final String URL = "jdbc:postgresql://109.97.198.79:5432/cc_shop";
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
