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

/**
 *
 * @author Eu
 */
public class DAODependentes extends AbstractDAO {

    public DAODependentes() {
    }

    //concluido - falta testar
    @Override
    public void salvar(EntidadeDominio entidade) {

        try {
            openConnection();
            Materia materia = (Materia) entidade;

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO dependentes(dep_materia_id, dep_dependencia_id)");
            sql.append(" VALUES (?,?)");

            pst = conexao.prepareStatement(sql.toString());
            pst.setInt(1, materia.getId());
            pst.setInt(2, materia.getDependencia());
            pst.executeUpdate();
            conexao.commit();
            System.out.println("cadastrado com sucesso");
        } catch (SQLException e) {
            try {
                System.out.println("Erro na inserção: " + e);
                conexao.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (ctrlTransaction) {
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
