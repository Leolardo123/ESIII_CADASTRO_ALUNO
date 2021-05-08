/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import static Dao.AbstractDAO.conexao;
import Dominio.Endereco;
import Dominio.EntidadeDominio;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eu
 */
public class DAOEndereco extends AbstractDAO{
    public DAOEndereco(){}
    
    public void salvar(EntidadeDominio entidade){
        try {
            if(conexao == null) openConnection();
            Endereco end = (Endereco)entidade;
            StringBuilder sql = new StringBuilder();

            sql.append("INSERT INTO Enderecos(cep, estado, cidade, numero");
            sql.append("logradouro, complemento, dt_Cadastro)");
            sql.append(" VALUES (?, ?, ?, ?, ?,?)");	
            conexao.setAutoCommit(false);


            pst = conexao.prepareStatement(sql.toString(), 
                            Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, end.getCep());
            pst.setString(2, end.getEstado());
            pst.setString(3, end.getCidade());
            pst.setInt(4, end.getNumero());
            pst.setString(5, end.getComplemento());
            Timestamp time = new Timestamp(end.getDtcadastro().getTime());
            pst.setTimestamp(6, time);
            pst.executeUpdate();		

            rs = pst.getGeneratedKeys();
            int idEnd=0;
            if(rs.next())
                    idEnd = rs.getInt(1);
            end.setId(idEnd);

            conexao.commit();					
        } catch (SQLException e) {
                try {
                        conexao.rollback();
                } catch (SQLException e1) {
                        e1.printStackTrace();
                }
                e.printStackTrace();	
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOEndereco.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ctrlTransaction){
                try {
                    closeConnection();
                } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
            }
        }
    }

    public void alterar(EntidadeDominio entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void consultar(EntidadeDominio entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void excluir(EntidadeDominio entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
