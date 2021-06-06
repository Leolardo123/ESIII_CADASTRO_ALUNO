/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import static Dao.AbstractDAO.conexao;
import Dominio.Aluno;
import Dominio.Curso;
import Dominio.EntidadeDominio;
import Dominio.GradeCurso;
import Dominio.ItemGrade;
import Dominio.Materia;
import Dominio.Professor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Eu
 */
public class DAOGrade extends AbstractDAO {

    public DAOGrade() {
        table = "grade_curso";
        prefixo = "gra_";
        id_table = prefixo + "id";
    }

    //concluido - falta testar
    @Override
    public void salvar(EntidadeDominio entidade) {
        GradeCurso grade = (GradeCurso) entidade;
        try {
            DAOItemGrade daoItem = new DAOItemGrade();
            daoItem.ctrlTransaction = false;

            openConnection();
            conexao.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO grade_curso(gra_semestre,gra_cur_id)");
            sql.append(" VALUES (?,?)");

            pst = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, grade.getSemestre());
            pst.setInt(2, grade.getCurso().getId());
            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();

            if (rs.next()) {
                grade.setId(rs.getInt(id_table));
            }

            daoItem.salvar(grade);

            daoItem.ctrlTransaction = true;
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
        GradeCurso grade = (GradeCurso) entidade;

        this.ctrlTransaction = false;
        for (ItemGrade itemGrade : grade.getItens()) {
            try {
                conexao.setAutoCommit(false);

                StringBuilder sql = new StringBuilder();
                sql.append("UPDATE grade_curso ");
                sql.append(table);
                sql.append("SET gra_obrigatorio = ?, gra_turno = ?, gra_dia_semana = ?, gra_semestre = ?,");
                sql.append("gra_periodo, gra_cur_id = ?, gra_mat_id = ?, gra_pro_id = ? ");
                sql.append("WHERE ");
                sql.append(id_table);
                sql.append("= ?");

                pst = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
                pst.setBoolean(1, itemGrade.isObrigatorio());
                pst.setInt(2, itemGrade.getTurno());
                pst.setInt(3, itemGrade.getDia_semana());
                pst.setInt(4, grade.getSemestre());
                pst.setInt(5, itemGrade.getPeriodo());
                pst.setInt(6, grade.getCurso().getId());
                pst.setInt(7, itemGrade.getMateria().getId());
                pst.setInt(8, itemGrade.getProfessor().getId());
                pst.executeUpdate();

                ResultSet rs = pst.getGeneratedKeys();

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
    }

    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        GradeCurso grade = (GradeCurso) entidade;
        DAOItemGrade daoItem = new DAOItemGrade();

        try {
            openConnection();

            conexao.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();

            if (grade != null && grade.getCurso() != null && grade.getCurso().getId() != 0) {
                sql.append("SELECT * FROM " + table + " WHERE gra_cur_id   = " + grade.getCurso().getId() + " ORDER BY " + id_table);
            } else if (grade.getId() != 0) {
                sql.append("SELECT * FROM " + table + " WHERE " + id_table + " = " + entidade.getId() + " ORDER BY " + id_table);
            } else {
                sql.append("SELECT * FROM " + table + " ORDER BY " + id_table);
            }

            pst = conexao.prepareStatement(sql.toString());
            ResultSet rs = pst.executeQuery();

            List<EntidadeDominio> ListaGrade = new ArrayList<EntidadeDominio>();

            while (rs.next()) {
                Curso curso = new Curso();

                DAOCurso DAOcur = new DAOCurso();
                curso = (Curso) DAOcur.consultar(curso).get(0);
                grade.setId(rs.getInt("gra_id"));

                List<EntidadeDominio> entidadeItens = daoItem.consultar(grade);
                List<ItemGrade> itens = new ArrayList<ItemGrade>();

                if (entidadeItens != null) {
                    for (EntidadeDominio entidadeItem : entidadeItens) {
                        itens.add((ItemGrade) entidadeItem);
                    }
                }

                grade = new GradeCurso(itens, curso, rs.getInt("gra_semestre"));

                grade.setId(rs.getInt("gra_id"));
                curso.setId(rs.getInt("gra_cur_id"));

                ListaGrade.add(grade);
            }

            return ListaGrade;
        } catch (SQLException e) {
            try {
                System.out.println("Erro ao recuperar: " + e);
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
        return null;
    }

}
