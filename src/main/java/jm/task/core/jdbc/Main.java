package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main  {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();



            userService.createUsersTable();

            userService.saveUser("Aleksandr", "Kurma", (byte) 99);
            userService.saveUser("James", "Gosling", (byte) 65);
            userService.saveUser("Sergei", "Nikonov", (byte) 1);

            List<User> userList = userService.getAllUsers();
            for (User user : userList) {
                System.out.println(user.toString());
            }
            userService.cleanUsersTable();
            userService.dropUsersTable();


    }
}
