package ru.chaban.jdbc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Demo {
    private static final String URL = "jdbc:h2:mem:";
    private static Logger logger = LoggerFactory.getLogger(Demo.class);
    private final Connection connection;

    public Demo() throws SQLException {
        this.connection = DriverManager.getConnection(URL);
        this.connection.setAutoCommit(false);
    }

    public static void main(String[] args) throws SQLException {
        Demo demo = new Demo();
        demo.createTable();


    }

    private void createTable() throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(
                "create table test(id int, name varchar2(100))")) {
            pst.executeUpdate();
        }
    }
}
