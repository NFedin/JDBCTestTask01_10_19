package com.test.jdbc.repository;

import com.test.jdbc.entity.UserEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public interface IRepository {

    UserEntity findByLogin(String userName, Statement statement) throws SQLException, ClassNotFoundException ;
    boolean updateSecondName(String userName, String change, Statement statement) throws SQLException, ClassNotFoundException ;
}
