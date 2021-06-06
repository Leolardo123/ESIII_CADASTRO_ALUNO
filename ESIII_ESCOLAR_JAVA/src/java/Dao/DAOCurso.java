/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import static Dao.AbstractDAO.conexao;
import Dominio.Curso;
import Dominio.EntidadeDominio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Eu
 */
public class DAOCurso extends AbstractDAO {

    public DAOCurso() {
        prefixo = "cur_";
        table = "cursos";
        id_table = prefixo+"id";
    }

    //Concluido - falta testar
    @Override
    public void salvar(EntidadeDominio entidade) {
        Curso curso = (Curso) entidade;
        openConnection();

        try {
            conexao.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO cursos(cur_nome, cur_descricao, cur_nivel, ");
            sql.append("cur_duracao, cur_mensalidade)");
            sql.append(" VALUES (?,?,?,?,?)");
            pst = conexao.prepareStatement(sql.toString());
            pst.setString(1, curso.getNome());
            pst.setString(2, curso.getDescricao());
            pst.setString(3, curso.getNivel());
            pst.setInt(4, curso.getDuracao());
            pst.setDouble(5, curso.getMensalidade());
            pst.executeUpdate();

            conexao.commit();
            
            System.out.println("cadastrado com sucesso");
        } catch (SQLException e) {
            try {
                System.out.println("Erro na inserção: " + e);
                conexao.rollback();
            } catch (SQLException e1) {
            }
        } finally {
            try {
                closeConnection();
            } catch (SQLException e) {
            }
        }
    }

    public void alterar(EntidadeDominio entidade) {
        Curso curso = (Curso) entidade;

        try {
            openConnection();
            
            conexao.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE cursos SET  cur_nome = ?, cur_descricao = ?, cur_nivel = ?, ");
            sql.append("cur_duracao = ?, cur_mensalidade = ? WHERE cur_id = ?");
            pst = conexao.prepareStatement(sql.toString());
            pst.setString(1, curso.getNome());
            pst.setString(2, curso.getDescricao());
            pst.setString(3, curso.getNivel());
            pst.setInt(4,   curso.getDuracao());
            pst.setDouble(5, curso.getMensalidade());
            pst.setInt(6, curso.getId());
            pst.executeUpdate();

            conexao.commit();
            
            System.out.println("alterado com sucesso");
        } catch (SQLException e) {
            try {
                System.out.println("Erro na alteração: " + e);
                conexao.rollback();
            } catch (SQLException e1) {
            }
        } finally {
            try {
                closeConnection();
            } catch (SQLException e) {
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
            
            List<EntidadeDominio> cursos = new ArrayList<EntidadeDominio>();
            
            while(rs.next()){
                Curso curso = new Curso(rs.getString("cur_nome"),rs.getString("cur_descricao"),
                        rs.getString("cur_nivel"),rs.getInt("cur_duracao"), rs.getDouble("cur_mensalidade"));
                
                curso.setId(rs.getInt(id_table));
                curso.setDtcadastro(rs.getDate("cur_dtcadastro"));
                
                cursos.add(curso);
            }
            
            return cursos;
        } catch (SQLException e) {
            try {
                System.out.println("Erro na pesquisa: " + e);
                conexao.rollback();
            } catch (SQLException e1) {
            }
        } finally {
            try {
                closeConnection();
            } catch (SQLException e) {
            }
        }
        return null;
    }
    
    @Override
    public void excluir(EntidadeDominio entidade){
    }
}
