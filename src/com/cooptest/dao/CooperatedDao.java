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
public class CooperatedDao extends MySqlDatabaseConnector{
    private String sql;
    
    public ResultSet findAll(){
        try{
            sql = "select * from cooperado";
            
            setStatement(getConnection().prepareStatement(sql));
            
            setResultSet(getStatement().executeQuery());
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return getResultSet();
    }
    
    public ResultSet findById(int id){
        try{
            sql = 
                "select " +
                "   * " +
                "from " +
                "   cooperado coo " +
                "join nacionalidade nac " +
                "   on nac.id = coo.id_nacionalidade " +
                "join conceito con " +
                "   on con.id = coo.id_conceito " +
                "join endereco en " +
                "   on en.id = coo.id_endereco " +
                "join tipo_pessoa tp " +
                "   on tp.id = coo.id_tipo_pessoa " +
                "where " +
                "   coo.id = ? ";
            
            setStatement(getConnection().prepareStatement(sql));
            
            getStatement().setInt(1, id);
            
            setResultSet(getStatement().executeQuery());
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return getResultSet();
    }
    
    public ResultSet findByName(String name){
        try{
            sql = 
                "select " +
                "   * " +
                "from " +
                "   cooperado coo " +
                "join nacionalidade nac " +
                "   on nac.id = coo.id_nacionalidade " +
                "join conceito con " +
                "   on con.id = coo.id_conceito " +
                "join endereco en " +
                "   on en.id = coo.id_endereco " +
                "join tipo_pessoa tp " +
                "   on tp.id = coo.id_tipo_pessoa " +
                "where " +
                "   coo.nome like ? ";
            
            setStatement(getConnection().prepareStatement(sql));
            
            getStatement().setString(1, name + "%");
            
            setResultSet(getStatement().executeQuery());
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return getResultSet();
    }
}
