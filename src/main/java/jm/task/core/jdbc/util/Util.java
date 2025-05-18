package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // set up a database connection
    // JDBC
    private static final String url = "jdbc:mysql://localhost:3306/ProjectHabsida";
    private static final String user = "root";
    private static final String password = "Mfghost66";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при подключении к БД", e);
        }
    }

    //Hibernate
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                configuration.setProperty("hibernate.connection.url", url);
                configuration.setProperty("hibernate.connection.username", user);
                configuration.setProperty("hibernate.connection.password", password);
                configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
                configuration.setProperty("hibernate.show_sql", "true");

                configuration.addAnnotatedClass(User.class);

                sessionFactory = configuration
                        .buildSessionFactory(new StandardServiceRegistryBuilder()
                                .applySettings(configuration.getProperties())
                                .build());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Ошибка создания SessionFactory");
            }
        }
        return sessionFactory;
    }
}
