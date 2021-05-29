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
import Dominio.Pessoa;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Eu
 */
public class DAOMateria extends AbstractDAO {

    public DAOMateria() {
        table = "materias";
        prefixo ="mat_";
        id_table = prefixo+"id";
    }

    //concluido - falta testar
    @Override
    public void salvar(EntidadeDominio entidade) {
        Materia materia = (Materia) entidade;

        try {
            openConnection();
            conexao.setAutoCommit(false);
            DAODependentes DAOdep = new DAODependentes();
            DAOdep.ctrlTransaction = false;
       

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO materias(mat_nome, mat_descricao, mat_carga_horaria)");
            sql.append(" VALUES (?,?,?)");

            pst = conexao.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, materia.getNome());
            pst.setString(2, materia.getDescricao());
            pst.setInt(3, materia.getCarga_horaria());
            
            pst.executeUpdate();
            
            ResultSet rs = pst.getGeneratedKeys();
            
            if(rs.next()){
                materia.setId(rs.getInt(id_table));
            }
            
            if(materia.getDependencias().size()>0){
                DAOdep.salvar(materia);
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
        Materia materia = (Materia) entidade;

        try {
            openConnection();
            conexao.setAutoCommit(false);
            DAODependentes DAOdep = new DAODependentes();
            DAOdep.ctrlTransaction = false;
       

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE materias SET mat_nome = ?, mat_descricao = ?, mat_carga_horaria  = ?");
            sql.append("WHERE "+id_table+" = ? ");

            pst = conexao.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, materia.getNome());
            pst.setString(2, materia.getDescricao());
            pst.setInt(3, materia.getCarga_horaria());
            pst.setInt(4, materia.getId());
            pst.executeUpdate();
            
            ResultSet rs = pst.getGeneratedKeys();
            
            if(rs.next()){
                materia.setId(rs.getInt(id_table));
            }
            
            conexao.commit();
            System.out.println("alterado com sucesso");
            DAOdep.ctrlTransaction = true;
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
    
    public List<EntidadeDominio> consultar(EntidadeDominio entidade){
        Materia materia = (Materia)entidade;
        try {
            openConnection();
            
            conexao.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            if (entidade == null || entidade.getId() == 0) {
                sql.append("SELECT * FROM "+table);
            }else if(entidade.getId()== 0){
                sql.append("SELECT * FROM "+table+" WHERE "+id_table+" = "+entidade.getId());
            }else {
                sql.append("SELECT * FROM "+table+" WHERE mat_nome = ?");
            }
            pst = conexao.prepareStatement(sql.toString());
            ResultSet rs = pst.executeQuery();
            
            List<EntidadeDominio> materias = new ArrayList<EntidadeDominio>();
            
            while(rs.next()){
               DAODependentes DAOdep = new DAODependentes();
               Materia dependencia = new Materia();
               dependencia.setId(rs.getInt("mat_id"));
               List<EntidadeDominio> EntidadeDependencias = DAOdep.consultar(dependencia);
               
               List<Materia> dependencias = new ArrayList();
               for(EntidadeDominio entidadeMateria: EntidadeDependencias){//recupera as dependencias da materia
                   dependencias.add((Materia)entidadeMateria);
               }
               
               materia = new Materia(rs.getString("mat_nome"), rs.getString("mat_descricao"), 
                                             rs.getInt("mat_carga_horaria"), dependencias);
               
               materia.setId(rs.getInt("mat_id"));
               materia.setDtcadastro(rs.getDate("mat_dtcadastro"));
               
               materias.add(materia);
            }
            
            return materias;
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
    
    public List<EntidadeDominio> consultaRestrita(EntidadeDominio entidade){//Não está pronto!
    try {
            String getCountDependencias = "SELECT COUNT(*),mat_id,mat_nome FROM dependentes LEFT JOIN materias ON dep_materia_id = mat_id GROUP BY mat_id";
            openConnection();
            
            conexao.setAutoCommit(false);
            
            StringBuilder sql = new StringBuilder();

            sql.append("SELECT * FROM "+table+" LEFT JOIN grade_curso ON ");
            sql.append("(SELECT COUNT(*) FROM dependentes LEFT JOIN materias ON dep_materia_id = materia_id) = ");
            sql.append("(SELECT COUNT(*) FROM grade_curso LEFT JOIN dependentes ");
            sql.append(" ON dep_dependencia_id =  gra_mat_id WHERE dep_materia_id = ? )");
            sql.append("WHERE gra_semestre < ?");
            pst = conexao.prepareStatement(sql.toString());
            ResultSet rs = pst.executeQuery();
            
            List<EntidadeDominio> materias = new ArrayList<EntidadeDominio>();
            
            while(rs.next()){
               DAODependentes DAOdep = new DAODependentes();
               Materia dependencia = new Materia();
               dependencia.setId(rs.getInt("mat_id"));
               List<EntidadeDominio> EntidadeDependencias = DAOdep.consultar(dependencia);
               
               List<Materia> dependencias = new ArrayList();
               for(EntidadeDominio entidadeMateria: EntidadeDependencias){//recupera cada materia que contém a partir da entidadeDominio
                   dependencias.add((Materia)entidadeMateria);
               }
               
               Materia materia = new Materia(rs.getString("mat_nome"), rs.getString("mat_descricao"), 
                                             rs.getInt("mat_carga_horaria"), dependencias);
               
               materia.setId(rs.getInt("mat_id"));
               materia.setDtcadastro(rs.getDate("mat_dtcadastro"));
               
               materias.add(materia);
            }
            
            return materias;
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
