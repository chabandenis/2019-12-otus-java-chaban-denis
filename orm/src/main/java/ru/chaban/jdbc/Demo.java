package ru.chaban.jdbc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

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
        demo.insertRecord(1);
        demo.selectRecord(1);
        demo.close();
    }

    private void close() throws SQLException {
        this.connection.close();
    }

    private void createTable() throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(
                "create table test(id int, name varchar2(100))")) {
            pst.executeUpdate();
        }
    }

    private void insertRecord(int id) throws SQLException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("insert into test(id, name) values (?, ?)")) {
            Savepoint savepoint = this.connection.setSavepoint("myPoint");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, "NameValue");

            try {
                int rowCount = preparedStatement.executeUpdate();
                this.connection.commit();
                logger.info("inserted rowCount: {} ", rowCount);
            } catch (SQLException ex) {
                this.connection.rollback(savepoint);
                logger.error(ex.getMessage(), ex);
            }
        }
    }

    private void selectRecord(int id) throws SQLException {
        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement("select name from test where id = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                StringBuilder ourString = new StringBuilder();
                ourString.append("name: ");
                if (rs.next()) {
                    ourString.append(rs.getString("name"));
                }
                logger.info(ourString.toString());
            }
        }
    }
}
