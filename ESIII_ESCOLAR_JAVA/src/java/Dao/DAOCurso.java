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
import java.sql.ResultSet;
import java.sql.SQLException;
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
                if(this.ctrlTransaction)closeConnection();
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
            sql.append("UPDATE cursos SET  cur_descricao = ?, cur_nivel = ?, ");
            sql.append("cur_duracao = ?, cur_mensalidade = ? WHERE cur_id = ?");
            pst = conexao.prepareStatement(sql.toString());
            pst.setString(1, curso.getDescricao());
            pst.setString(2, curso.getNivel());
            pst.setInt(3,   curso.getDuracao());
            pst.setDouble(4, curso.getMensalidade());
            pst.setInt(5, curso.getId());
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
                if(this.ctrlTransaction)closeConnection();
            } catch (SQLException e) {
            }
        }
    }

    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        try {
            openConnection();
            
            conexao.setAutoCommit(false);
            
            StringBuilder sql = new StringBuilder();
            
            if (entidade != null && entidade.getId() != 0) {
                if(entidade instanceof Curso){
                    Curso curso = (Curso)entidade;
                    if(curso.getNome()!=null){
                        sql.append("SELECT * FROM "+table+" WHERE cur_nome = '");
                        sql.append(curso.getNome());
                        sql.append("' ORDER BY cur_id ");
                    }else{
                        sql.append("SELECT * FROM "+table+" WHERE cur_id = ");
                        sql.append(curso.getId());
                        sql.append(" ORDER BY cur_id ");
                    }
                }else
                if(entidade instanceof Aluno){
                    sql.append("SELECT * FROM "+table+" LEFT JOIN alunos ");
                    sql.append(" ON alu_cur_id = cur_id ");
                    sql.append(" WHERE alu_pes_id = ");
                    sql.append(entidade.getId());
                }
            } else {
                sql.append("SELECT * FROM "+table);
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
                if(this.ctrlTransaction)closeConnection();
            } catch (SQLException e) {
            }
        }
        return null;
    }
}
