/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import static Dao.AbstractDAO.conexao;
import Dominio.Endereco;
import Dominio.Pessoa;
import Dominio.EntidadeDominio;
import Dominio.Pessoa;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Eu
 */
public class DAOPessoa extends AbstractDAO {

    public DAOPessoa() {
        table = "pessoas";
        prefixo ="pes_";
        id_table = prefixo+"id";
    }

    @Override
    public void salvar(EntidadeDominio entidade) {
        Pessoa   pessoa   = (Pessoa) entidade;
        Endereco endereco = pessoa.getEndereco();

        try {
            openConnection();
            DAOEndereco DAOend = new DAOEndereco();
            DAOend.ctrlTransaction = false;
            DAOend.salvar(endereco);

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO pessoas(pes_pnome, pes_unome,  pes_cpf, pes_rg, ");
            sql.append("pes_email, pes_dtnascimento, pes_end_id)");
            sql.append(" VALUES (?,?,?,?,?,?,?)");

            pst = conexao.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, pessoa.getPnome());
            pst.setString(2, pessoa.getUnome());
            pst.setString(3, pessoa.getCpf());
            pst.setString(4, pessoa.getRg());
            pst.setString(5, pessoa.getEmail());
            pst.setDate(6, new java.sql.Date(pessoa.getDtNascimento().getTime()));
            pst.setInt(7, endereco.getId());
            pst.executeUpdate();
            
            ResultSet rs = pst.getGeneratedKeys();
            
            if(rs.next()){
                pessoa.setId(rs.getInt(id_table));
            }
            
            System.out.println("cadastrado com sucesso");
        } catch (SQLException e) {
            try {
                System.out.println("Erro na inserção: " + e);
                conexao.rollback();
            } catch (SQLException e1) {
            }
            e.printStackTrace();
        } finally {
            if (ctrlTransaction) {
                try {
                    if(this.ctrlTransaction)closeConnection();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public void alterar(EntidadeDominio entidade) {
        Pessoa   pessoa   = (Pessoa) entidade;
        Endereco endereco =  pessoa.getEndereco();

        try {
            openConnection();
            DAOEndereco DAOend = new DAOEndereco();
            DAOend.ctrlTransaction = false;
            DAOend.alterar(endereco);

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE pessoas SET pes_pnome = ?, pes_unome = ?, ");
            sql.append("pes_email = ?, pes_dtnascimento = ?, pes_end_id = ?  WHERE pes_id = ? ");

            pst = conexao.prepareStatement(sql.toString());
            pst.setString(1, pessoa.getPnome());
            pst.setString(2, pessoa.getUnome());
            pst.setString(3, pessoa.getEmail());
            pst.setDate(4, new java.sql.Date(pessoa.getDtNascimento().getTime()));
            pst.setInt(5, endereco.getId());
            pst.setInt(6, pessoa.getId());
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
            if (ctrlTransaction) {
                try {
                    if(this.ctrlTransaction)closeConnection();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    
    public List<EntidadeDominio> consultar(EntidadeDominio entidade){
        Pessoa pessoa = (Pessoa)entidade;
        try {
            openConnection();
            conexao.setAutoCommit(false);
            
            StringBuilder sql = new StringBuilder();
            if ((entidade == null || entidade.getId() == 0) && (pessoa.getCpf() == null && pessoa.getRg() == null)) {
                sql.append("SELECT * FROM "+table);
            } else if(pessoa.getCpf() == null && pessoa.getRg() == null) {
                sql.append("SELECT * FROM "+table+" WHERE "+id_table+" = " + entidade.getId() + "");
            } else if(pessoa.getCpf() != null && pessoa.getRg() != null){
                sql.append("SELECT * FROM " + table + " WHERE pes_rg = '");
                sql.append(pessoa.getRg());
                sql.append("' OR pes_cpf = '");
                sql.append(pessoa.getCpf());
                sql.append("'");
            }else{
                return null;
            }
            
            pst = conexao.prepareStatement(sql.toString());
            ResultSet rs = pst.executeQuery();
            
            List<EntidadeDominio> pessoas = new ArrayList<EntidadeDominio>();
            
            while(rs.next()){
               DAOEndereco DAOend = new DAOEndereco();
               Endereco endereco = new Endereco();
               endereco.setId(rs.getInt("pes_end_id"));
               endereco =(Endereco)DAOend.consultar(endereco).get(0);
               
                pessoa = new Pessoa(rs.getString("pes_cpf"),rs.getString("pes_rg"),rs.getString("pes_pnome"),
                       rs.getString("pes_unome"),rs.getString("pes_email"),rs.getDate("pes_dtnascimento"),endereco);
               
               pessoa.setId(rs.getInt("pes_id"));
               pessoa.setDtcadastro(rs.getDate("pes_dtcadastro"));
               
               pessoas.add(pessoa);
            }
            
            return pessoas;
        } catch (SQLException e) {
            try {
                System.out.println("Erro ao recuperar: " + e);
                conexao.rollback();
            } catch (SQLException e1) {
            }
            e.printStackTrace();
        } finally {
            try {
                if(this.ctrlTransaction)closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    public void excluir(EntidadeDominio entidade) {
        Pessoa pessoa = (Pessoa)entidade;
        try {
            openConnection();
            
            DAOEndereco DAOend = new DAOEndereco();
            DAOend.ctrlTransaction = false;

            conexao.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();

            sql.append("DELETE FROM " + table + " WHERE ");
            sql.append(id_table);
            sql.append(" = ");
            sql.append(entidade.getId());
            pst = conexao.prepareStatement(sql.toString());
            
            DAOend.excluir(pessoa.getEndereco());
            
            pst.executeUpdate();
        } catch (SQLException e) {
            try {
                System.out.println("Erro ao excluir: " + e);
                conexao.rollback();
            } catch (SQLException e1) {
            }
            e.printStackTrace();
        } finally {
            try {
                if(this.ctrlTransaction)closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
