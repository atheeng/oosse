package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static DataBaseConnection dbc;
    private static Connection con;
    private String url = "jdbc:mysql://localhost:3306/online_order";
    private String username = "root";
    private String password ="root";

   private DataBaseConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage() +"-"+ex.getCause());
        }
    }

    public static Connection getConnection() {
        return con;
    }
    public static DataBaseConnection getInstance() throws SQLException {
        if (dbc == null) {
            dbc = new DataBaseConnection();
        } else if (dbc.getConnection().isClosed()) {
            dbc = new DataBaseConnection();
        }

        return dbc;
    }
}
