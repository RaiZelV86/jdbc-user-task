package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // set up a database connection
    // JDBC
    private static final String URL = "jdbc:mysql://localhost:3306/ProjectHabsida";
    private static final String USER = "root";
    private static final String PASSWORD = "mfg86";

    //Hibernate
    private static SessionFactory sessionFactory;

    private Util() {}

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("JDBC connection failed", e);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {

            try {
                Properties props = new Properties();

                props.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                props.put("hibernate.connection.url", URL);
                props.put("hibernate.connection.username", USER);
                props.put("hibernate.connection.password", PASSWORD);

                props.put("hibernate.hbm2ddl.auto", "none");
                props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
                props.put("hibernate.show_sql", "true");
                props.put("hibernate.format_sql", "true");

                Configuration configuration = new Configuration();
                configuration.setProperties(props);
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
