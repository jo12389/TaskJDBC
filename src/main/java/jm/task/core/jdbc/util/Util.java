package jm.task.core.jdbc.util;
//import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

/*public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";

    public static Connection getConnect() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        System.out.println("создалось новое соединение");
        return connection;
    }
}*/
public class Util {

    private static Util instance;
    private Connection connection;
    private final static String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";

    private Util() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Util getInstance() throws SQLException {
        if (instance == null) {
            instance = new Util();
        } else if (instance.getConnection().isClosed()) {
            instance = new Util();
        }

        return instance;
    }
}