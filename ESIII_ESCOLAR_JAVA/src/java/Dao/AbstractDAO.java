/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dominio.EntidadeDominio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Eu
 */
public abstract class AbstractDAO implements IDAO{
    protected static Connection conexao = null;
    protected static PreparedStatement pst = null;
    protected static ResultSet rs = null;
    protected boolean ctrlTransaction=true;
    
    protected void conectar() throws ClassNotFoundException, SQLException{
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ESIII_ESCOLAR"
                , "postgres", "postgres");
        conexao = conn;
    }
    
    protected void openConnection()throws ClassNotFoundException, SQLException{
        if(conexao == null || conexao.isClosed()) conectar();
    }
    
    protected static void closeConnection()throws SQLException{
        if(conexao != null) conexao.close();
        if(pst != null) pst.close();
        if(rs != null) rs.close();
    }
    
    public void excluir() {}

}
