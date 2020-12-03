package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static java.lang.Integer.valueOf;

public class Main {

    private static UserService userService = new UserServiceImpl();



    public static void main(String[] args) throws SQLException {




        userService.cleanUsersTable();

        userService.dropUsersTable();

        userService.createUsersTable();
        System.out.println("Таблица создана");

        userService.saveUser("Ignat", "Vasilevich", Byte.parseByte("64"));
        System.out.println("добавлен");

        userService.saveUser("Petya", "Perviy", Byte.parseByte("64"));
        System.out.println("добавлен");

        userService.saveUser("Iosif", "Stalin", Byte.parseByte("64"));
        System.out.println("добавлен");

        userService.removeUserById(1);

        System.out.println(userService.getAllUsers());










    }
}
