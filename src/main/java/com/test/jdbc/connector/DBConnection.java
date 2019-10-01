package com.test.jdbc.connector;

import java.sql.*;

public class DBConnection {

    private static Connection connection;
    private static Statement statement;

    public static void connectDB(String JDBC_DRIVER, String DATABASE_URL, String USER, String PASSWORD) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        statement = connection.createStatement();
    }

    public static Statement getStatementDB(){
       	return statement;
    }

    public static void closeDB() throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
