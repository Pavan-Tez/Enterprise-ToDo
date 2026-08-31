package com.enterprise.todo.config;

import java.sql.Connection;

import javax.sql.DataSource;

public class DatabaseTest {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void testConnection() throws Exception {
        try(Connection connection = dataSource.getConnection()){
             System.out.println("========== DATABASE CONNECTED ==========");
            System.out.println(connection.getMetaData().getDatabaseProductName());
            System.out.println(connection.getMetaData().getDatabaseProductVersion());
            System.out.println("========================================");
        }
    }
}
