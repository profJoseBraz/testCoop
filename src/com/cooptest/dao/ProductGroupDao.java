/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cooptest.dao;

import com.cooptest.database.util.MySqlDatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jose_
 */
public class ProductGroupDao extends MySqlDatabaseConnector{
    private String sql;
    
    public ResultSet findAll(){
        try{
            sql = "select * from grupo_produto";
            
            setStatement(getConnection().prepareStatement(sql));
            
            setResultSet(getStatement().executeQuery());
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return getResultSet();
    }
    
    public ResultSet findByName(String name){
        try{
            sql = "select * from grupo_produto where nome like ?";
            
            setStatement(getConnection().prepareStatement(sql));
            
            getStatement().setString(1, name + "%");
            
            setResultSet(getStatement().executeQuery());
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return getResultSet();
    }
}
