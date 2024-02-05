package com.nikron.tennis.listener;

import com.nikron.tennis.util.BuildSessionFactoryUtil;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class DbInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
        BuildSessionFactoryUtil.createSessionFactory();
    }
}
