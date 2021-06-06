/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import static Dao.AbstractDAO.conexao;
import Dominio.Aluno;
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
public class DAOProfessor extends AbstractDAO {

    public DAOProfessor() {
        table = "professores";
        prefixo ="pro_";
        id_table = prefixo+"pes_id";
    }

    //concluido - falta testar
    @Override
    public void salvar(EntidadeDominio entidade) {
        Professor professor = (Professor) entidade;
        Pessoa pessoa = (Pessoa) entidade;

        try {
            openConnection();
            conexao.setAutoCommit(false);
            DAOPessoa DAOpes = new DAOPessoa();
            DAOpes.ctrlTransaction = false;
            DAOpes.salvar(pessoa);

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO professores(pro_salario, pro_pes_id)");
            sql.append(" VALUES (?,?)");

            pst = conexao.prepareStatement(sql.toString());
            pst.setDouble(1, professor.getSalario());
            pst.setInt(2, professor.getId());
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
        Professor professor = (Professor) entidade;
        Pessoa pessoa = (Pessoa) entidade;

        try {
            openConnection();
            conexao.setAutoCommit(false);
            DAOPessoa DAOpes = new DAOPessoa();
            DAOpes.ctrlTransaction = false;
            DAOpes.alterar(pessoa);

            StringBuilder sql = new StringBuilder();
            sql.append(" UPDATE professores SET pro_salario = ? WHERE pro_pes_id = ?");

            pst = conexao.prepareStatement(sql.toString());
            pst.setDouble(1, professor.getSalario());
            pst.setInt(2, professor.getId());
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
            
            List<EntidadeDominio> professores = new ArrayList<EntidadeDominio>();
            
            while(rs.next()){
               DAOPessoa DAOpes = new DAOPessoa();
               Pessoa pessoa = new Pessoa();
               pessoa.setId(rs.getInt("pro_pes_id"));
               pessoa = (Pessoa)DAOpes.consultar(pessoa).get(0);

               Professor professor = new Professor(pessoa,rs.getFloat("pro_salario"));
               professor.setId(pessoa.getId());
               professor.setDtcadastro(pessoa.getDtcadastro());
               professores.add(professor);
            }
            
            return professores;
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
     @Override
    public void excluir(EntidadeDominio entidade) {
        try {
            openConnection();

            conexao.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();

            sql.append("DELETE FROM " + table + " WHERE ");
            sql.append(id_table);
            sql.append(" = ");
            sql.append(entidade.getId());
            pst = conexao.prepareStatement(sql.toString());
            pst.executeUpdate();
            
            DAOPessoa DAOpes = new DAOPessoa();
            DAOpes.excluir((Pessoa)entidade);
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
    }
}
