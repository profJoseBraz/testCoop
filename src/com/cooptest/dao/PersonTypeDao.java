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
public class PersonTypeDao extends MySqlDatabaseConnector{
    private String sql;
    
    public ResultSet findByName(String name){
        try{
            sql = 
                "select * from tipo_pessoa where nome like ?";
            
            setStatement(getConnection().prepareStatement(sql));
            
            getStatement().setString(1, name);
            
            setResultSet(getStatement().executeQuery());
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return getResultSet();
    }
}
