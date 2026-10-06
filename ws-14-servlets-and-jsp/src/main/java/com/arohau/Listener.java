package com.arohau;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("INITIALIZE ALL RESOURCES JUST ONCE");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("RELEASE ALL RESOURCES");
    }
}