package model;

import java.sql.*;

public class Database {

    // Database connection method
    public static Connection connectDatabase()
    {
        Connection connection = null;
        System.out.println("Attempting to connect...");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/new_molby?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            System.out.println("Connection done !");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connection error");
        }

        return connection;
    }
}
