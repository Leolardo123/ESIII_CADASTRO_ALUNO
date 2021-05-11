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
/**
 *
 * @author Eu
 */
public class DAOGrade extends AbstractDAO {

    public DAOGrade() {
    }

    //concluido - falta testar
    @Override
    public void salvar(EntidadeDominio entidade) {
        GradeCurso grade = (GradeCurso) entidade;

        try {
            conexao.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO grade_curso(gra_obrigatorio, gra_turno, gra_dia_semana, ");
            sql.append("gra_periodo, gra_cur_id, gra_mat_id, gra_pro_id)");
            sql.append(" VALUES (?,?,?,?,?,?,?)");

            pst = conexao.prepareStatement(sql.toString());
            pst.setBoolean(1, grade.isObrigatorio());
            pst.setInt(2, grade.getTurno());
            pst.setInt(3, grade.getDia_semana());
            pst.setInt(4, grade.getPeriodo());
            pst.setInt(5, grade.getCurso_id());
            pst.setInt(6, grade.getMateria_id());
            pst.setInt(7, grade.getProfessor_id());
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

    public void consultar(EntidadeDominio entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void excluir(EntidadeDominio entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
