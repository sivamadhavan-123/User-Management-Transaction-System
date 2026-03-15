package org.example.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppStartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            LiquibaseRunner.runLiquibase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}