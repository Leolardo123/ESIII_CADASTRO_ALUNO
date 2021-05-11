/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import static Dao.AbstractDAO.conexao;
import Dominio.Curso;
import Dominio.EntidadeDominio;
import java.sql.SQLException;
/**
 *
 * @author Eu
 */
public class DAOCurso extends AbstractDAO {

    public DAOCurso() {
    }

    //Concluido - falta testar
    @Override
    public void salvar(EntidadeDominio entidade) {
        Curso curso = (Curso) entidade;
        openConnection();

        try {
            conexao.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO cursos(cur_nome, cur_descricao, cur_nivel, ");
            sql.append("cur_duracao, cur_mensalidade)");
            sql.append(" VALUES (?,?,?,?,?,?)");
            pst = conexao.prepareStatement(sql.toString());
            pst.setString(1, curso.getNome());
            pst.setString(2, curso.getDescricao());
            pst.setString(3, curso.getNivel());
            pst.setInt(4, curso.getDuracao());
            pst.setDouble(5, curso.getMensalidade());
            pst.executeUpdate();

            conexao.commit();
            System.out.println("cadastrado com sucesso");
        } catch (SQLException e) {
            try {
                System.out.println("Erro na inserção: " + e);
                conexao.rollback();
            } catch (SQLException e1) {
            }
        } finally {
            try {
                closeConnection();
            } catch (SQLException e) {
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
