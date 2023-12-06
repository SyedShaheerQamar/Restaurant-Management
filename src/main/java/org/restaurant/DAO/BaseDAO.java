package org.restaurant.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {

    static final String DB_URL = "jdbc:mysql://localhost:3306/restaurant";
    static final String USER = "root";
    static final String PASS = "password@123";
    Connection conn;

    public BaseDAO(){
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
