/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import static Dao.AbstractDAO.conexao;
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
 * @author 55119
 */
public class DAOItemGrade extends AbstractDAO{
    
    public DAOItemGrade() {
        table = "grade_itens";
        prefixo = "gri_";
    }

    //concluido - falta testar
    @Override
    public void salvar(EntidadeDominio entidade) {
        GradeCurso grade = (GradeCurso) entidade;
        this.ctrlTransaction = false;
        for(ItemGrade itemGrade:grade.getItens()){
            try {
                openConnection();
                conexao.setAutoCommit(false);

                StringBuilder sql = new StringBuilder();
                sql.append("INSERT INTO grade_itens(gra_obrigatorio, gra_turno, gra_dia_semana,");
                sql.append("gra_periodo, gra_cur_id, gra_mat_id, gra_pro_id)");
                sql.append(" VALUES (?,?,?,?,?,?,?)");

                pst = conexao.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
                pst.setBoolean(1, itemGrade.isObrigatorio());
                pst.setInt(2, itemGrade.getTurno());
                pst.setInt(3, itemGrade.getDia_semana());
                pst.setInt(4, itemGrade.getPeriodo());
                pst.setInt(5, grade.getCurso().getId());
                pst.setInt(6, itemGrade.getMateria().getId());
                pst.setInt(7, itemGrade.getProfessor().getId());
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
    }

    public void alterar(EntidadeDominio entidade) {
        GradeCurso grade = (GradeCurso) entidade;

        this.ctrlTransaction = false;
        for(ItemGrade itemGrade:grade.getItens()){
            try {
                conexao.setAutoCommit(false);

                StringBuilder sql = new StringBuilder();
                sql.append("UPDATE ");
                sql.append(table);
                sql.append(" SET gri_obrigatorio = ?, gri_turno = ?, gri_dia_semana = ?, gri_semestre = ?,");
                sql.append("gri_periodo, gri_cur_id = ?, gri_mat_id = ?, gri_pro_id = ? ");
                sql.append("WHERE ");
                sql.append("gri_cur_id = ?, gri_mat_id = ?");

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
        GradeCurso grade = (GradeCurso)entidade;
        try {
            openConnection();
            
            conexao.setAutoCommit(false);
            
            StringBuilder sql = new StringBuilder();
            
            if (grade == null || grade.getCurso() == null|| grade.getCurso().getId() == 0) {
                sql.append("SELECT * FROM "+table);
            } else if(grade.getId()!=0) {
                sql.append("SELECT * FROM "+table+" WHERE "+id_table+" = " + grade.getCurso().getId() + "");
            }
            
            pst = conexao.prepareStatement(sql.toString());
            ResultSet rs = pst.executeQuery();
          
            List<EntidadeDominio> ListaGrade = new ArrayList<EntidadeDominio>(); 
            HashMap<Integer,GradeCurso> organizaGrade = new HashMap<Integer,GradeCurso>();
            
            while(rs.next()){
               DAOProfessor DAOpro = new DAOProfessor();
               DAOCurso     DAOcur = new DAOCurso();
               DAOMateria   DAOmat = new DAOMateria();
               
               try{
                   Professor professor = new Professor();
                   Curso     curso     = new Curso();
                   Materia   materia   = new Materia();
                   
                   professor.setId(rs.getInt("gri_pro_id"));
                   curso.setId(rs.getInt("gri_cur_id"));
                   materia.setId(rs.getInt("gri_mat_id"));
                   
                   professor = (Professor)DAOpro.consultar(professor).get(0);
                   curso     = (Curso)    DAOcur.consultar(curso).get(0);
                   materia   = (Materia)  DAOmat.consultar(materia).get(0);
                   
                   ItemGrade item = new ItemGrade(rs.getBoolean("gri_obrigatorio"),rs.getInt("gri_dia_semana"),
                   rs.getInt("gri_turno"),rs.getInt("gri_periodo"), materia, professor);
                   
               }catch(Exception ex){
                   System.out.print("Erro na consulta do item da grade_curso:"+ex);
               }
            }
            
            for(Map.Entry<Integer,GradeCurso> gradeCurso:organizaGrade.entrySet()){
                ListaGrade.add(gradeCurso.getValue());
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
