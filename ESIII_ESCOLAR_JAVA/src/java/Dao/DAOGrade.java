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
                if(this.ctrlTransaction)closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void alterar(EntidadeDominio entidade) {
        GradeCurso grade = (GradeCurso) entidade;

        DAOItemGrade DAOIgd = new DAOItemGrade();
        
        DAOIgd.ctrlTransaction = false;

        List<EntidadeDominio> existsItens = DAOIgd.consultar(grade);
        List<EntidadeDominio> itensRemovidos = new ArrayList<EntidadeDominio>();

        if (existsItens != null) {
            for (EntidadeDominio entidadeItem : existsItens) {
                ItemGrade novoItem = new ItemGrade();
                boolean exists = false;
                for (ItemGrade itemGrade : grade.getItens()) {
                    if (entidadeItem.getId() == itemGrade.getId()) {
                        novoItem = null;
                        exists = true;
                    }else{
                        novoItem = itemGrade;
                    }
                }
                if (exists == false) {
                    DAOIgd.excluir(entidadeItem);
                    DAOIgd.salvar(novoItem);
                }
            }
        }
        
        for (ItemGrade itemGrade : grade.getItens()) {
            try {
                conexao.setAutoCommit(false);

                StringBuilder sql = new StringBuilder();
                sql.append("UPDATE grade_curso ");
                sql.append(table);
                sql.append("SET gra_semestre = ?,");
                sql.append("gra_cur_id = ?");
                sql.append("WHERE ");
                sql.append(id_table);
                sql.append("= ?");

                pst = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
                pst.setInt(1, grade.getSemestre());
                pst.setInt(2, grade.getCurso().getId());
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
                    if(this.ctrlTransaction)closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        Curso curso = new Curso();
        GradeCurso grade = new GradeCurso();
        if(entidade instanceof GradeCurso){
            grade = (GradeCurso) entidade;
        }
        if(entidade instanceof Curso){
            curso = (Curso) entidade;
        }
        DAOItemGrade daoItem = new DAOItemGrade();
        try {
            openConnection();

            conexao.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();

            if (curso != null && curso.getId() != 0) {
                sql.append("SELECT * FROM " + table + " WHERE gra_cur_id   = " + curso.getId() + " ORDER BY " + id_table);
            } else if (grade != null && grade.getId() != 0) {
                sql.append("SELECT * FROM " + table + " WHERE " + id_table + " = " + entidade.getId() + " ORDER BY " + id_table);
            } else {
                sql.append("SELECT * FROM " + table + " ORDER BY " + id_table);
            }

            pst = conexao.prepareStatement(sql.toString());
            ResultSet rs = pst.executeQuery();

            List<EntidadeDominio> ListaGrade = new ArrayList<EntidadeDominio>();

            while (rs.next()) {
                grade = new GradeCurso();
                
                if(entidade instanceof Curso){
                    grade.setCurso(curso);
                }else{
                    DAOCurso DAOcur = new DAOCurso();
                    curso = new Curso();
                    
                    curso.setId(rs.getInt("gra_cur_id"));
                    curso = (Curso) DAOcur.consultar(curso).get(0);
                } 
    
                grade.setCurso(curso);
                grade.setSemestre(rs.getInt("gra_semestre"));
                grade.setId(rs.getInt("gra_id"));

                List<EntidadeDominio> entidadeItens = daoItem.consultar(grade);
                List<ItemGrade> itens = new ArrayList<ItemGrade>();

                if (entidadeItens != null) {
                    for (EntidadeDominio entidadeItem : entidadeItens) {
                        itens.add((ItemGrade) entidadeItem);
                    }
                }

                grade.setItens(itens);

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
                if(this.ctrlTransaction)closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
