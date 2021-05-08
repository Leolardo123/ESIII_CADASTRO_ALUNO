/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import static Dao.AbstractDAO.conexao;
import Dominio.EntidadeDominio;
import Dominio.Materia;
import Dominio.Pessoa;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author Eu
 */
public class DAODependentes extends AbstractDAO{
    public DAODependentes(){}
    
    public void salvar(EntidadeDominio entidade) {
        Materia materia = (Materia)entidade;

        try {
                conexao.setAutoCommit(false);	
                DAODependentes DAOdep = new DAODependentes();
                DAOdep.ctrlTransaction = false;
                DAOdep.salvar(materia);

                StringBuilder sql = new StringBuilder();
                sql.append("INSERT INTO Dependentes(mat_nome, mat_descricao, mat_carga_horaria, mat_dtcadastro)");
                sql.append(" VALUES (?,?)");		

                pst = conexao.prepareStatement(sql.toString());
                pst.setString(1, materia.getNome());
                pst.setString(2, materia.getDescricao());
                pst.setInt(3, materia.getCarga_horaria());
                Timestamp time = new Timestamp(materia.getDtcadastro().getTime());
                pst.setTimestamp(4, time);
                pst.executeUpdate();			
                conexao.commit();		
        } catch (SQLException e) {
                try {
                        conexao.rollback();
                } catch (SQLException e1) {
                        e1.printStackTrace();
                }
                e.printStackTrace();			
        }finally{
                try {
                    closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
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
