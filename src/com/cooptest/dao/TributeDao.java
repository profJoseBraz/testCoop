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
public class TributeDao extends MySqlDatabaseConnector{
    private String sql;
    
    public ResultSet findByTributeCriteria(int idUfFrom, int idUfTo, int idProductGroup, int idPersonType){
        try{
            sql = 
                "select " +
                "   * " +
                "from " +
                "   tributacao t " +
                "where " +
                "   uf_origem = ? and " +
                "   uf_destino = ? and " +
                "   id_grupo_produto = ? and " +
                "   id_tipo_pessoa = ?";
            
            setStatement(getConnection().prepareStatement(sql));
            
            getStatement().setInt(1, idUfFrom);
            getStatement().setInt(2, idUfTo);
            getStatement().setInt(3, idProductGroup);
            getStatement().setInt(4, idPersonType);
            
            setResultSet(getStatement().executeQuery());
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return getResultSet();
    }
}
