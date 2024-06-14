/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cooptest.database.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author jose_
 */
public class MySqlDatabaseConnector {
    private static final String URL = 
        "jdbc:mysql://localhost:3306/coop_bd?useSSL="
      + "false&allowPublicKeyRetrieval=true";
    
    private static final String USER = "root";
    private static final String PASS = "admin";
    
    private static Connection connection = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;

    public Connection loadJdbcDriver() {
        connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar o driver JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro ao obter a conexão com o banco de dados: " + e.getMessage());
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
            }
        }
    }
    
    public boolean connect(){
        setConnection(loadJdbcDriver());
        
        if (getConnection() != null){
            System.out.println("Conexão com o banco de dados estabelecida.");
            return true;
        }else{
            return false;
        }
    }
    
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        MySqlDatabaseConnector.connection = connection;
    }

    public PreparedStatement getStatement() {
        return statement;
    }

    public void setStatement(PreparedStatement statement) {
        MySqlDatabaseConnector.statement = statement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet result) {
        MySqlDatabaseConnector.resultSet = result;
    }
}
