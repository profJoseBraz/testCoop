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
public class ProductDao extends MySqlDatabaseConnector{
    private String sql;
    
    public ResultSet findAll(){
        try{
            sql = 
                "select " +
                " * " +
                "from " +
                "   produto pro " +
                "join grupo_produto gp " +
                "   on gp.id = pro.id_grupo_produto " +
                "join unidade_medida um " +
                "   on um.id = pro.id_unidade_medida " +
                "join formula_princ_ativ fpa " +
                "   on fpa.id = pro.id_formula_princ_ativ ";
            
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
                " * " +
                "from " +
                "   produto pro " +
                "join grupo_produto gp " +
                "   on gp.id = pro.id_grupo_produto " +
                "join unidade_medida um " +
                "   on um.id = pro.id_unidade_medida " +
                "join formula_princ_ativ fpa " +
                "   on fpa.id = pro.id_formula_princ_ativ " +
                "where " +
                "   pro.id = ?";
            
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
                " * " +
                "from " +
                "   produto pro " +
                "join grupo_produto gp " +
                "   on gp.id = pro.id_grupo_produto " +
                "join unidade_medida um " +
                "   on um.id = pro.id_unidade_medida " +
                "join formula_princ_ativ fpa " +
                "   on fpa.id = pro.id_formula_princ_ativ " +
                "where " +
                "   pro.nome_comercial like ?";
            
            setStatement(getConnection().prepareStatement(sql));
            
            getStatement().setString(1, name + "%");
            
            setResultSet(getStatement().executeQuery());
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return getResultSet();
    }
}
