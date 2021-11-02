package com.ss.lms.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public final String driver = "com.mysql.cj.jdbc.Driver";
    public final String url = "jdbc:mysql://localhost:3306/library";
    public final String username = "admin";
    public final String password = "123456";

    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver); // optional - good practice
        Connection conn = DriverManager.getConnection(url, username,password);
        conn.setAutoCommit(Boolean.FALSE);
        return conn;
    }
}
