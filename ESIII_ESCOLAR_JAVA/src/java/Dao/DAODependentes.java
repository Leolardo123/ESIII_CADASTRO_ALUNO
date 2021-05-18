/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import static Dao.AbstractDAO.conexao;
import Dominio.Endereco;
import Dominio.EntidadeDominio;
import Dominio.Materia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eu
 */
public class DAODependentes extends AbstractDAO {

    public DAODependentes() {
        table = "dependentes";
        id_table = "dep_dependencia_id";
    }

    //concluido - falta testar
    @Override
    public void salvar(EntidadeDominio entidade) {//Espera receber uma entidade de uma matéria!
        
        try {
            openConnection();
            Materia materia = (Materia) entidade;
            
            conexao.setAutoCommit(false);
            
            if(materia.getDependencias()!=null){
                for(Materia dependencia:materia.getDependencias()){
                    StringBuilder sql = new StringBuilder();
                    sql.append("INSERT INTO "+table+"(dep_materia_id, dep_dependencia_id)");
                    sql.append(" VALUES (?,?)");

                    pst = conexao.prepareStatement(sql.toString());
                    pst.setInt(1, materia.getId());
                    pst.setInt(2, dependencia.getId());
                    pst.executeUpdate();
                }
            }

            conexao.commit();
            
            System.out.println("cadastrado com sucesso");
        } catch (SQLException e) {
            try {
                System.out.println("Erro na inserção: " + e);
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
    
    public List<EntidadeDominio> consultar() {
            try {
            openConnection();
            
            StringBuilder sql = new StringBuilder();

            sql.append("SELECT * FROM "+table+" LEFT JOIN materias ON mat_id = "+id_table);
            pst = conexao.prepareStatement(sql.toString());
            ResultSet rs = pst.executeQuery();
            
            List<EntidadeDominio> dependencias = new ArrayList<EntidadeDominio>();
            
            while(rs.next()){
               Materia dependencia = new Materia(rs.getString("mat_nome"),rs.getString("mat_descricao"),
                                                 rs.getInt("mat_carga_horaria"));
               
               dependencia.setId(rs.getInt("mat_id"));
               dependencia.setDtcadastro(rs.getDate("mat_dtcadastro"));
               
               dependencias.add(dependencia);
            }
            
            return dependencias;
        } catch (SQLException e) {
            System.out.println("Erro ao recuperar: " + e);
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

    public List<EntidadeDominio> consultar(int id) {
                    try {
            openConnection();
            
            conexao.setAutoCommit(false);
            
            StringBuilder sql = new StringBuilder();

            sql.append("SELECT * FROM "+table+" LEFT JOIN materias ON mat_id = "+id_table+" WHERE dep_materia_id = "+id);
            pst = conexao.prepareStatement(sql.toString());
            ResultSet rs = pst.executeQuery();
            
            List<EntidadeDominio> dependencias = new ArrayList<EntidadeDominio>();
            
            while(rs.next()){
                
               Materia dependencia = new Materia(rs.getString("mat_nome"),rs.getString("mat_descricao"),
                                                 rs.getInt("mat_carga_horaria"));
               
               dependencia.setId(rs.getInt("mat_id"));
               dependencia.setDtcadastro(rs.getDate("mat_dtcadastro"));
               
               dependencias.add(dependencia);
               
               System.out.println("DEP_DEPENDENCIA_ID:"+dependencia.getId());
            }
            
            return dependencias;
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
