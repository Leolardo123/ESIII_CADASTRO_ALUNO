/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import static Dao.AbstractDAO.conexao;
import Dominio.EntidadeDominio;
import Dominio.Materia;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eu
 */
public class DAODependentes extends AbstractDAO{
    public DAODependentes(){}
    
    public void salvar(EntidadeDominio entidade) {

        try {
                if(conexao == null) openConnection();
                Materia materia = (Materia)entidade;

                StringBuilder sql = new StringBuilder();
                sql.append("INSERT INTO Dependentes(dep_materia_id, dep_dependencia_id)");
                sql.append(" VALUES (?,?)");		

                pst = conexao.prepareStatement(sql.toString());
                pst.setInt(1, materia.getId());
                pst.setInt(2, materia.getDependencia());
                pst.executeUpdate();			
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
