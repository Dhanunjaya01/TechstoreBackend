package com.techstore.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://reseau.proxy.rlwy.net:41708/railway?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "ZiOhaLyqrPUEHXCGVkjDYOurjNVubmRD";

    private static Connection connection;

    public static Connection getConnection() {

        try {

            if (connection == null || connection.isClosed()) {

                Class.forName("com.mysql.cj.jdbc.Driver");

                connection = DriverManager.getConnection(
                        URL,
                        USERNAME,
                        PASSWORD
                );

                System.out.println("✅ Database Connected Successfully");

            }

        } catch (ClassNotFoundException e) {

            System.out.println("Driver Not Found");

            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("Database Connection Failed");

            e.printStackTrace();

        }

        return connection;
    }

}