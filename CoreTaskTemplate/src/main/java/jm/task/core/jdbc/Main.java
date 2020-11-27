package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static UserService userService = new UserServiceImpl();


    public static void main(String[] args) throws SQLException {

        userService.cleanUsersTable();
        System.out.println("Таблица очищена");

        userService.dropUsersTable();
        System.out.println("Таблица дропнута");

        userService.createUsersTable();
        System.out.println("Таблица создана");

        userService.saveUser("Bob", "Bobovich", (byte) 17);
        System.out.println("user с именем Bob добавлен в БД");
        userService.saveUser("Vasya", "Terkin", (byte) 21);
        System.out.println("user с именем Vasya добавлен в БД");
        userService.saveUser("Jack", "Gogenov", (byte) 15);
        System.out.println("user Jack добавлен в БД");
        userService.saveUser("Angella", "Rahmatovna", (byte) 44);
        System.out.println("user Angella добавлен в БД");

        userService.removeUserById(1);
        System.out.println("Пользователь c id 1 удален");

        System.out.println(userService.getAllUsers());
    }
}
