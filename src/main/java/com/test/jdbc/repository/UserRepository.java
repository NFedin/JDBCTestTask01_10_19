package com.test.jdbc.repository;

import com.test.jdbc.entity.UserEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository implements IRepository {

    public UserEntity findByLogin(String userName, Statement statement) throws SQLException, ClassNotFoundException, NullPointerException {

        if(statement == null) {
            throw new NullPointerException("Cannot connect to database");
        }
        UserEntity userEntity = null;

        if (userName != null) {
            String sql = "SELECT * FROM users WHERE login = \"" + userName + "\"";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()){
                userEntity = UserEntity.builder()
                        .login(resultSet.getString("login"))
                        .firstName(resultSet.getString("first_name"))
                        .secondName(resultSet.getString("second_name"))
                        .middleName(resultSet.getString("middle_name"))
                        .age(resultSet.getInt("age"))
                        .email(resultSet.getString("email"))
                        .phoneNumber(resultSet.getString("phone_number"))
                        .build();
            }
        }
        return userEntity;
    }

    public boolean updateSecondName(String userName, String change, Statement statement) throws SQLException, NullPointerException {

        if(statement == null) {
              throw new NullPointerException("Cannot connect to database");
        }
        if (statement != null && userName != null) {
            String sql = "UPDATE users SET second_name=\"" + change + "\" WHERE login=\"" + userName + "\"";
            if (statement.executeUpdate(sql)>0) {
                return true;
            }else {
                return false;
            }
        } 
        return false;
    }
}

