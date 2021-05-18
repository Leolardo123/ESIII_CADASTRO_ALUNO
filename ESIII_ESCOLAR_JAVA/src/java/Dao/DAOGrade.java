/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import static Dao.AbstractDAO.conexao;
import Dominio.Aluno;
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
            pst.setInt(5, grade.getCurso_id());
            pst.setInt(6, grade.getMateria_id());
            pst.setInt(7, grade.getProfessor_id());
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<EntidadeDominio> consultar(int id) {
         try {
            openConnection();
            
            conexao.setAutoCommit(false);
            
            StringBuilder sql = new StringBuilder();

            sql.append("SELECT * FROM "+table+"WHERE "+id_table+" = ?");
            pst = conexao.prepareStatement(sql.toString());
            ResultSet rs = pst.executeQuery();
          
            List<EntidadeDominio> ListaGrade = new ArrayList<EntidadeDominio>(); 
            
            while(rs.next()){
               DAOProfessor DAOpro = new DAOProfessor();
               DAOAluno     DAOalu = new DAOAluno();
               DAOMateria   DAOmat = new DAOMateria();
               
               try{
                   Professor professor = (Professor)DAOpro.consultar(rs.getInt("gra_pro_id")).get(0);
                   Aluno     aluno     = (Aluno)  DAOalu.consultar(rs.getInt("gra_pro_id")).get(0);
                   Materia   materia   = (Materia)DAOmat.consultar(rs.getInt("gra_pro_id")).get(0);
               }catch(Exception ex){
                   System.out.print("Erro na consulta do item da grade_curso:"+ex);
               }
               
               GradeCurso grade = new GradeCurso(rs.getBoolean("gra_obrigatorio"),rs.getInt("gra_dia_semana"),
                       rs.getInt("gra_turno"),rs.getInt("gra_periodo"), rs.getInt("gra_cur_id"), rs.getInt("gra_mat_id"),
                       rs.getInt("gra_pro_id"));
                       
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
    
    public List<EntidadeDominio> consultar() {
         try {
            openConnection();
            
            conexao.setAutoCommit(false);
            
            StringBuilder sql = new StringBuilder();

            sql.append("SELECT * FROM "+table);
            pst = conexao.prepareStatement(sql.toString());
            ResultSet rs = pst.executeQuery();
          
            List<EntidadeDominio> ListaGrade = new ArrayList<EntidadeDominio>(); 
            
            while(rs.next()){
               DAOProfessor DAOpro = new DAOProfessor();
               DAOAluno     DAOalu = new DAOAluno();
               DAOMateria   DAOmat = new DAOMateria();
               
               try{
                   Professor professor = (Professor)DAOpro.consultar(rs.getInt("gra_pro_id")).get(0);
                   Aluno     aluno     = (Aluno)  DAOalu.consultar(rs.getInt("gra_pro_id")).get(0);
                   Materia   materia   = (Materia)DAOmat.consultar(rs.getInt("gra_pro_id")).get(0);
               }catch(Exception ex){
                   System.out.print("Erro na consulta do item da grade_curso:"+ex);
               }
               
               GradeCurso grade = new GradeCurso(rs.getBoolean("gra_obrigatorio"),rs.getInt("gra_dia_semana"),
                       rs.getInt("gra_turno"),rs.getInt("gra_periodo"), rs.getInt("gra_cur_id"), rs.getInt("gra_mat_id"),
                       rs.getInt("gra_pro_id"));
                       
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
