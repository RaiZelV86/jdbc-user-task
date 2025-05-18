package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // implement algorithm here
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Ivan", "Petrov", (byte) 25);
        System.out.println("Пользователь с именем – Ivan добавлен в базу данных");

        userService.saveUser("Anna", "Ivanova", (byte) 30);
        System.out.println("Пользователь с именем – Anna добавлен в базу данных");

        userService.saveUser("Sergey", "Smirnov", (byte) 28);
        System.out.println("Пользователь с именем – Sergey добавлен в базу данных");

        userService.saveUser("Elena", "Kuznetsova", (byte) 22);
        System.out.println("Пользователь с именем – Elena добавлен в базу данных");

        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }

       userService.cleanUsersTable();
       userService.dropUsersTable();
    }
}
