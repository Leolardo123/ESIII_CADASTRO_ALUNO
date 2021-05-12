/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import static Dao.AbstractDAO.conexao;
import Dominio.EntidadeDominio;
import Dominio.Pessoa;
import Dominio.Professor;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Eu
 */
public class DAOProfessor extends AbstractDAO {

    public DAOProfessor() {
        table = "professores";
        id_table = "pro_pes_id";
    }

    //concluido - falta testar
    @Override
    public void salvar(EntidadeDominio entidade) {
        Professor professor = (Professor) entidade;
        Pessoa pessoa = (Pessoa) entidade;

        try {
            openConnection();
            conexao.setAutoCommit(false);
            DAOPessoa DAOpes = new DAOPessoa();
            DAOpes.ctrlTransaction = false;
            DAOpes.salvar(pessoa);

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO professores(pro_salario, pro_pes_id)");
            sql.append(" VALUES (?,?)");

            pst = conexao.prepareStatement(sql.toString());
            pst.setDouble(1, professor.getSalario());
            pst.setInt(2, professor.getId());
            pst.executeUpdate();
            conexao.commit();
            System.out.println("cadastrado com sucesso");
        } catch (SQLException e) {
            try {
                System.out.println("Erro na inserção: " + e);
                conexao.rollback();
            } catch (SQLException e1) {
            }
            e.printStackTrace();
        } finally {
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

    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
