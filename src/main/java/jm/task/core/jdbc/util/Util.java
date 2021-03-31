package jm.task.core.jdbc.util;
//import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";
    public static Connection connection;

    public static Connection getConnect() throws ClassNotFoundException, SQLException {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);


            return connection;
        }
    }
