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
import java.sql.Timestamp;
/**
 *
 * @author Eu
 */
public class DAOCurso extends AbstractDAO{
    public DAOCurso(){}
    
    public void salvar(EntidadeDominio entidade) {
        Curso curso = (Curso) entidade;

        try {
            conexao.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO Cursos(cur_nome, cur_descricao, cur_nivel, ");
            sql.append("cur_duracao, cur_dtcadastro, cur_mensalidade");
            sql.append(" VALUES (?,?,?,?,?,?)");

            pst = conexao.prepareStatement(sql.toString());
            pst.setString(1, curso.getNome());
            pst.setString(2, curso.getDescricao());
            pst.setString(3, curso.getNivel());
            pst.setInt(4, curso.getDuracao());
            Timestamp time = new Timestamp(curso.getDtcadastro().getTime());
            pst.setTimestamp(5, time);
            pst.setDouble(6, curso.getMensalidade());
            pst.executeUpdate();

            conexao.commit();
        } catch (SQLException e) {
            try {
                conexao.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
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

    public void consultar(EntidadeDominio entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void excluir(EntidadeDominio entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
