package com.codecool.shop.dao;

import com.codecool.shop.config.ConfigurationManager;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DatabaseManager {

    private static DatabaseManager instance;

    private final String databaseUrl;
    private final String databaseName;
    private final String databaseUser;
    private final String databasePassword;

    private DataSource dataSource;

    private DatabaseManager() {
        databaseUrl = ConfigurationManager.getProperty("url");
        databaseName = ConfigurationManager.getProperty("database");
        databaseUser = ConfigurationManager.getProperty("user");
        databasePassword = ConfigurationManager.getProperty("password");
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public DataSource getDataSource() {
        if (dataSource != null) {
            return dataSource;
        }

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName(databaseName);
        dataSource.setUser(databaseUser);
        dataSource.setPassword(databasePassword);

        try {
            System.out.println("Trying to connect...");
            dataSource.getConnection().close();
            System.out.println("Connection OK");
        } catch (SQLException e) {
            throw new RuntimeException("Could not connect to db: " + e.getMessage());
        }

        return dataSource;
    }
}
