package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;


import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.cfg.Configuration;


import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {

    private static HibernateUtil instance;

    private static SessionFactory sessionFactory;

    private HibernateUtil () {

    }

    public static HibernateUtil getInstance() {

        if(instance == null) {

            instance = new HibernateUtil();

        }

        return instance;

    }

    public static SessionFactory getConfiguration() {

        if (sessionFactory == null) {

            sessionFactory = createSessionFactory();

        }

        return sessionFactory;

    }

@SuppressWarnings("UnusedDeclaration")

    private static Configuration getMySqlConfiguration() {

        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");

        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");

        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/pobeda?allowPublicKeyRetrieval=true&useSSL=false&characterEncoding=UTF-8&useUnicode=true&useSSL=false&serverTimezone=UTC");

        configuration.setProperty("hibernate.connection.username", "root");

        configuration.setProperty("hibernate.connection.password", "12345678");

        configuration.setProperty("hibernate.show_sql", "true");

        configuration.setProperty("hibernate.hbm2ddl.auto", "none");

        return configuration;

    }

    private static SessionFactory createSessionFactory() {

        Configuration configuration = getMySqlConfiguration();

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();

        builder.applySettings(configuration.getProperties());

        ServiceRegistry serviceRegistry = builder.build();

        return configuration.buildSessionFactory(serviceRegistry);

    }

}
