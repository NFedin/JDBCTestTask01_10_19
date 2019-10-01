package com.test.jdbc;

import com.test.jdbc.connector.DBConnection;
import com.test.jdbc.entity.UserEntity;
import com.test.jdbc.repository.UserRepository;

import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/company?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASSWORD = "12345";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.connectDB(JDBC_DRIVER,DATABASE_URL,USER, PASSWORD);
            Statement statement = DBConnection.getStatementDB();
            UserRepository userRepository = new UserRepository();
            UserEntity userEntity = userRepository.findByLogin("Dandy", statement);
            System.out.println(userEntity.toString());
            System.out.println("\n=====================\n");
            System.out.println(userRepository.updateSecondName("Puppey", "Ivanov", statement));
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Closing connection and releasing resources...");
            DBConnection.closeDB();
        }
    }
}

