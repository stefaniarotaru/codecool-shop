package com.codecool.shop.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {
    private static final Properties CONNECTION_PROPERTIES = new Properties();

    static {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String connectionConfigPath = rootPath + "connection.properties";
        try {
            CONNECTION_PROPERTIES.load(new FileInputStream(connectionConfigPath));
        } catch (IOException e) {
            throw new RuntimeException("Could not open connection properties file.");
        }
    }

    public static String getProperty(String propertyName) {
        return CONNECTION_PROPERTIES.getProperty(propertyName);
    }
}
