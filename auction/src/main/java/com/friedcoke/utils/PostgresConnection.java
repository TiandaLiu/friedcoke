package com.friedcoke.utils;

import java.sql.DriverManager;

public class PostgresConnection {
    public static java.sql.Connection connect() {
        java.sql.Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("building connection string");
            String connectionUrl = "jdbc:postgresql://127.0.0.1:5432/auctions";
            String connectionUser = "postgres";
            String connectionPassword = "mysecret";
            System.out.println("calling getConnection");
            conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}
