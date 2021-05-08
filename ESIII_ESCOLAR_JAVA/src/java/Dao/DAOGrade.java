/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import static Dao.AbstractDAO.conexao;
import Dominio.EntidadeDominio;
import Dominio.GradeCurso;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author Eu
 */
public class DAOGrade extends AbstractDAO{
    public DAOGrade(){}
    
    public void salvar(EntidadeDominio entidade) {
        GradeCurso grade = (GradeCurso) entidade;

        try {
            conexao.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO Cursos(cur_nome, cur_descricao, cur_nivel, ");
            sql.append("cur_duracao, cur_dtcadastro, cur_mensalidade");
            sql.append(" VALUES (?,?,?,?,?,?)");

            pst = conexao.prepareStatement(sql.toString());
            pst.setBoolean(1, grade.isObrigatorio());
            pst.setInt(2, grade.getTurno());
            pst.setInt(3, grade.getDia_semana());
            pst.setInt(4, grade.getPeriodo());
            Timestamp time = new Timestamp(grade.getDtcadastro().getTime());
            pst.setTimestamp(5, time);
            pst.setInt(6, grade.getCurso_id());
            pst.setInt(7, grade.getMateria_id());
            pst.setInt(8, grade.getProfessor_id());
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
