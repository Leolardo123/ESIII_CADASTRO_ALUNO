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
            this.ctrlTransaction = false;

            openConnection();
            conexao.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO grade_curso(gra_semestre,gra_cur_id)");
            sql.append(" VALUES (?,?)");

            pst = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, grade.getSemestre());
            pst.setInt(2, grade.getCurso_id());
            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();

            if (rs.next()) {
                grade.setId(rs.getInt(id_table));
            }

            daoItem.ctrlTransaction = true;
            daoItem.salvar(grade);
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
                if (this.ctrlTransaction) {
                    closeConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void alterar(EntidadeDominio entidade) {
        GradeCurso grade = (GradeCurso) entidade;

        try {
            this.ctrlTransaction = false;
            
            List<EntidadeDominio> entidadeGrade = this.consultar(grade);
            GradeCurso tempGrade = new GradeCurso();
            
            if(entidadeGrade!=null){
                tempGrade = (GradeCurso)entidadeGrade.get(0);
                if(tempGrade.equals(grade)){
                    if(tempGrade.getId()!=grade.getCurso_id()){
                        //se já existe a grade com semestre e curso igual mas com id diferente,deleta a grade anterior
                        //e todos seus itens (cascade no banco de dados),substituindo-a pela nova
                        System.out.println("Excluindo...");
                        return;
//                        this.excluir(tempGrade);
//                        
//                        StringBuilder sql = new StringBuilder();
//                        sql.append("UPDATE grade_curso ");//substituicao
//                        sql.append(table);
//                        sql.append(" SET gra_semestre = ?, gra_cur_id = ? ");
//                        sql.append(" WHERE ");
//                        sql.append(" gra_id = ?");
//
//                        pst = conexao.prepareStatement(sql.toString());
//                        pst.setInt(1, tempGrade.getSemestre());
//                        pst.setInt(2, tempGrade.getCurso_id());
//                        pst.setInt(3, grade.getId());
//                        pst.executeUpdate();
                    }
                }
            }
            
            
            openConnection();
            conexao.setAutoCommit(false);
            
            DAOItemGrade DAOigd = new DAOItemGrade();
            DAOigd.ctrlTransaction = false;

            List<EntidadeDominio> listItens = DAOigd.consultar(grade);
            List<ItemGrade> tempListItem    = grade.getItens();
            List<ItemGrade> existsItens     = new ArrayList<ItemGrade>();
            List<ItemGrade> alteredItens    = new ArrayList<ItemGrade>();
            
            for(EntidadeDominio entidadeItem:listItens){
                existsItens.add((ItemGrade)entidadeItem);
            }
            
            for(ItemGrade item:tempListItem){// A = Itens do banco de dados, B = Intens do Formulario
                if(existsItens.contains(item)){// Conj A = Conj B
                    alteredItens.add(item);//recebe os itens que existem no banco e na lista recebida
                }
            }
            
            grade.setItens( alteredItens);//coloca os itens em comum na grade para efetuar a alteração
            DAOigd.alterar(grade);
            
            existsItens.removeAll(tempListItem);//Conj B - Conj A = itens removidos
            grade.setItens(existsItens);//os itens que ja estao no banco mas não estão na lista que veio do formulario são removidos
            DAOigd.excluir(grade);
            
            for(EntidadeDominio entidadeItem:listItens){
                existsItens.add((ItemGrade)entidadeItem);
            }
            
            tempListItem.removeAll(existsItens);//Conj A - Conj B = itens novos
            grade.setItens(tempListItem);//os itens que não estão no BD mas estão na lista do formulário
            DAOigd.salvar(grade);
            
            conexao.commit();
            
            this.ctrlTransaction = true;
            System.out.println("alterado com sucesso");
        } catch (SQLException e) {
            try {
                System.out.println("Erro na alteração: " + e);
                conexao.rollback();
            } catch (SQLException e1) {
            }
            e.printStackTrace();
        } finally {
            try {
                if (this.ctrlTransaction) {
                    closeConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        try {
            this.ctrlTransaction = false;
                    
            openConnection();

            DAOItemGrade DAOigd = new DAOItemGrade();
            DAOigd.ctrlTransaction = false;

            conexao.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();
            if (entidade != null && entidade.getId() != 0) {
                if (entidade instanceof Curso) {
                    sql.append("SELECT * FROM " + table + " WHERE gra_cur_id   = " + entidade.getId() + " ORDER BY gra_cur_id ");
                } else if (entidade instanceof GradeCurso) {
                    GradeCurso grade = (GradeCurso) entidade;
                    if (grade.getSemestre() > 0 && grade.getCurso_id() != 0) {
                        sql.append("SELECT * FROM " + table + " WHERE gra_semestre = " + grade.getSemestre() + " AND gra_cur_id = " + grade.getCurso_id() +" ORDER BY gra_cur_id");
                    } else {
                        sql.append("SELECT * FROM " + table + " WHERE " + id_table + " = " + entidade.getId() + " ORDER BY gra_semestre");
                    }
                }
            } else {
                sql.append("SELECT * FROM " + table + " ORDER BY gra_cur_id");
            }

            pst = conexao.prepareStatement(sql.toString());
            ResultSet rs = pst.executeQuery();

            List<EntidadeDominio> ListaGrade = new ArrayList<EntidadeDominio>();

            while (rs.next()) {
                Curso curso = new Curso();
                GradeCurso grade = new GradeCurso();

                grade.setSemestre(rs.getInt("gra_semestre"));
                grade.setId(rs.getInt("gra_id"));

                List<EntidadeDominio> entidadeItens = DAOigd.consultar(grade);
                List<ItemGrade> itens = new ArrayList<ItemGrade>();

                if (entidadeItens != null) {
                    for (EntidadeDominio entidadeItem : entidadeItens) {
                        itens.add((ItemGrade) entidadeItem);
                    }
                }

                grade.setItens(itens);
                grade.setCurso_id(rs.getInt("gra_cur_id"));

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
                if (this.ctrlTransaction) {
                    closeConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
