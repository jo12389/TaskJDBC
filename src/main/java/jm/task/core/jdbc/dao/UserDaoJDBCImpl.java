package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Connection connection;

    {
        try {
            connection = Util.getConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Statement statement = connection.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS `mydbtest`.`new_table` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `lastname` VARCHAR(45) NOT NULL,\n" +
                    "  `age` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;\n");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE if exists mydbtest.new_table");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into mydbtest.new_table (name, lastname, age) VALUES ('" + name + "', '" + lastName + "', '" + age + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM `mydbtest`.`new_table` WHERE (`id` = '" + id + "' )");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from new_table");
            long id =1;
            while (resultSet.next()) {

                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                Byte age = resultSet.getByte("age");
                User user = new User(name,lastName,age);
                user.setId(id);

                userList.add(user);
                id++;




                // userList.add(new User(resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getByte("age")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("Delete  FROM mydbtest.new_table");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
