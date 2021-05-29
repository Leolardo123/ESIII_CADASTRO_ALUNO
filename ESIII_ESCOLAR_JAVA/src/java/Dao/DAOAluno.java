  
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import static Dao.AbstractDAO.conexao;
import Dominio.Aluno;
import Dominio.Endereco;
import Dominio.EntidadeDominio;
import Dominio.Pessoa;
import Dominio.Professor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Eu
 */
public class DAOAluno extends AbstractDAO {

    public DAOAluno() {
        prefixo = "alu_";
        table = "alunos";
        id_table = prefixo+"id";
    }

    //concluido - falta testar
    @Override
    public void salvar(EntidadeDominio entidade) {
        Aluno aluno = (Aluno) entidade;
        Pessoa pessoa = (Pessoa) entidade;
        openConnection();
        
        try {
            conexao.setAutoCommit(false);
            DAOPessoa DAOpes = new DAOPessoa();
            DAOpes.ctrlTransaction = false;
            DAOpes.salvar(pessoa);

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO alunos(alu_semestre, alu_pes_id, alu_cur_id)");
            sql.append(" VALUES (?,?,?)");

            pst = conexao.prepareStatement(sql.toString());
            pst.setInt(1, aluno.getSemestre());
            pst.setInt(2, pessoa.getId());
            pst.setInt(3, aluno.getCurso_id());
            pst.executeUpdate();
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
        Aluno aluno = (Aluno) entidade;
        Pessoa pessoa = (Pessoa) entidade;
        openConnection();
        
        try {
            conexao.setAutoCommit(false);
            DAOPessoa DAOpes = new DAOPessoa();
            DAOpes.ctrlTransaction = false;
            DAOpes.alterar(pessoa);
            
           

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE alunos SET alu_semestre = ?, alu_pes_id = ?, alu_cur_id = ? WHERE alu_pes_id = ?");

            pst = conexao.prepareStatement(sql.toString());
            pst.setInt(1, aluno.getSemestre());
            pst.setInt(2, pessoa.getId());
            pst.setInt(3, aluno.getCurso_id());
            pst.setInt(4, pessoa.getId());
            pst.executeUpdate();
            conexao.commit();
            System.out.println("alterado com sucesso");
        } catch (SQLException e) {
            try {
                System.out.println("Erro ao alterar: " + e);
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
            
            List<EntidadeDominio> alunos = new ArrayList<EntidadeDominio>();
            
            while(rs.next()){
               DAOPessoa DAOpes = new DAOPessoa();
               Pessoa pessoa = new Pessoa();
               pessoa.setId(rs.getInt("alu_pes_id"));
               pessoa = (Pessoa)DAOpes.consultar(pessoa).get(0);

               Aluno aluno = new Aluno(pessoa,rs.getInt("alu_semestre"),rs.getInt("alu_cur_id"));
               aluno.setId(pessoa.getId());
               aluno.setDtcadastro(pessoa.getDtcadastro());
               alunos.add(aluno);
            }
            
            return alunos;
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