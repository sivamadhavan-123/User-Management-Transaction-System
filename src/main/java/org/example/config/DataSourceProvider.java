package org.example.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.example.util.Property;

public class DataSourceProvider {

    private static final HikariDataSource data;

    static {
        HikariConfig config = new HikariConfig();

        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl(Property.getProperty("db.url"));
        config.setUsername(Property.getProperty("db.username"));
        config.setPassword(Property.getProperty("db.password"));


        config.setMaximumPoolSize(5);
        config.setMinimumIdle(2);
        config.setMaxLifetime(1800000);
        config.setConnectionTimeout(20000);
        config.setIdleTimeout(30000);
        data = new HikariDataSource(config);
    }
    public static HikariDataSource getDataSource() {
        return data;
    }
}
