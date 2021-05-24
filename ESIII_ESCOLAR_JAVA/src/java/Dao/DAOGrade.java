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
import Dominio.Materia;
import Dominio.Professor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Eu
 */
public class DAOGrade extends AbstractDAO {

    public DAOGrade() {
        table = "grade_curso";
        id_table = "gra_id";
    }

    //concluido - falta testar
    @Override
    public void salvar(EntidadeDominio entidade) {
        GradeCurso grade = (GradeCurso) entidade;

        try {
            openConnection();
            conexao.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO grade_curso(gra_obrigatorio, gra_turno, gra_dia_semana, ");
            sql.append("gra_periodo, gra_cur_id, gra_mat_id, gra_pro_id)");
            sql.append(" VALUES (?,?,?,?,?,?,?)");

            pst = conexao.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
            pst.setBoolean(1, grade.isObrigatorio());
            pst.setInt(2, grade.getTurno());
            pst.setInt(3, grade.getDia_semana());
            pst.setInt(4, grade.getPeriodo());
            pst.setInt(5, grade.getCurso().getId());
            pst.setInt(6, grade.getMateria().getId());
            pst.setInt(7, grade.getProfessor().getId());
            pst.executeUpdate();
            
            ResultSet rs = pst.getGeneratedKeys();
            
            if(rs.next()){
                grade.setId(rs.getInt(id_table));
            }

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

        try {
            conexao.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE grade_curso ");
            sql.append(table);
            sql.append("SET gra_obrigatorio, gra_turno, gra_dia_semana, ");
            sql.append("gra_periodo, gra_cur_id, gra_mat_id, gra_pro_id");
            sql.append("WHERE ");
            sql.append(id_table);
            sql.append("= ?");

            pst = conexao.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
            pst.setBoolean(1, grade.isObrigatorio());
            pst.setInt(2, grade.getTurno());
            pst.setInt(3, grade.getDia_semana());
            pst.setInt(4, grade.getPeriodo());
            pst.setInt(5, grade.getCurso().getId());
            pst.setInt(6, grade.getMateria().getId());
            pst.setInt(7, grade.getProfessor().getId());
            pst.executeUpdate();
            
            ResultSet rs = pst.getGeneratedKeys();
            
            if(rs.next()){
                grade.setId(rs.getInt(id_table));
            }

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

    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
         try {
            openConnection();
            
            conexao.setAutoCommit(false);
            
            StringBuilder sql = new StringBuilder();
            
           if (entidade == null || entidade.getId() == 0) {
                sql.append("SELECT * FROM "+table);
            } else {
                sql.append("SELECT * FROM "+table+" WHERE "+id_table+" = " + entidade.getId() + "");
            }
            
            pst = conexao.prepareStatement(sql.toString());
            ResultSet rs = pst.executeQuery();
          
            List<EntidadeDominio> ListaGrade = new ArrayList<EntidadeDominio>(); 
            
            while(rs.next()){
               DAOProfessor DAOpro = new DAOProfessor();
               DAOCurso     DAOcur = new DAOCurso();
               DAOMateria   DAOmat = new DAOMateria();
               
               try{
                   Professor professor = new Professor();
                   Curso     curso     = new Curso();
                   Materia   materia   = new Materia();
                   
                   professor.setId(rs.getInt("gra_pro_id"));
                   curso.setId(rs.getInt("gra_cur_id"));
                   materia.setId(rs.getInt("gra_mat_id"));
                   
                   professor = (Professor)DAOpro.consultar(professor).get(0);
                   curso     = (Curso)    DAOcur.consultar(curso).get(0);
                   materia   = (Materia)  DAOmat.consultar(materia).get(0);
                   
                   GradeCurso grade = new GradeCurso(rs.getBoolean("gra_obrigatorio"),rs.getInt("gra_dia_semana"),
                   rs.getInt("gra_turno"),rs.getInt("gra_periodo"), curso, materia,
                   professor);
                   
                   ListaGrade.add(grade);
                   
               }catch(Exception ex){
                   System.out.print("Erro na consulta do item da grade_curso:"+ex);
               }
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
