package org.example.config;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.example.util.Property;

import java.sql.Connection;

public class LiquibaseRunner {
    public static void runLiquibase() {
        try{
            Connection connection = DataSourceProvider.getDataSource().getConnection();

            Database database = DatabaseFactory.getInstance()
                    .findCorrectDatabaseImplementation(new JdbcConnection(connection));

            Liquibase liquibase = new Liquibase(Property.getProperty("liquibase.changelog"),new ClassLoaderResourceAccessor(),database);

            liquibase.update("");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
