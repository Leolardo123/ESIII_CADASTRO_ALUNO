/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import static Dao.AbstractDAO.conexao;
import Dominio.Endereco;
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
public class DAOEndereco extends AbstractDAO {

    public DAOEndereco() {
        table = "enderecos";
        prefixo ="end_";
        id_table = prefixo+"id";
    }

    //concluido - falta testar
    @Override
    public void salvar(EntidadeDominio entidade) {
        try {
            openConnection();
            Endereco end = (Endereco) entidade;
            StringBuilder sql = new StringBuilder();

            sql.append("INSERT INTO "+table+"(end_cep, end_estado, end_cidade, end_numero, ");
            sql.append("end_logradouro, end_complemento)");
            sql.append(" VALUES (?, ?, ?, ?, ?,?)");
            conexao.setAutoCommit(false);

            pst = conexao.prepareStatement(sql.toString(),
                    Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, end.getCep());
            pst.setString(2, end.getEstado());
            pst.setString(3, end.getCidade());
            pst.setInt(4, end.getNumero());
            pst.setString(5, end.getLogradouro());
            pst.setString(6, end.getComplemento());
            pst.executeUpdate();

            rs = pst.getGeneratedKeys();
            int idEnd = 0;
            if (rs.next()) {
                idEnd = rs.getInt(id_table);
            }
            end.setId(idEnd);

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
                    closeConnection();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public void alterar(EntidadeDominio entidade) {
         try {
            openConnection();
            Endereco end = (Endereco) entidade;
            StringBuilder sql = new StringBuilder();

            sql.append("UPDATE ");
            sql.append(table);
            sql.append(" SET end_cep = ?, end_estado = ?, end_cidade = ?,");
            sql.append(" end_numero = ?, end_logradouro = ?, end_complemento = ? ");
            sql.append(" WHERE end_id = ?");
            conexao.setAutoCommit(false);

            pst = conexao.prepareStatement(sql.toString());

            pst.setString(1, end.getCep());
            pst.setString(2, end.getEstado());
            pst.setString(3, end.getCidade());
            pst.setInt(4, end.getNumero());
            pst.setString(5, end.getLogradouro());
            pst.setString(6, end.getComplemento());
            pst.setInt(7, end.getId());
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
                    closeConnection();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    
    public List<EntidadeDominio> consultar(EntidadeDominio entidade){
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
            
            List<EntidadeDominio> enderecos = new ArrayList<EntidadeDominio>();
            
            while(rs.next()){
               Endereco endereco = new Endereco(rs.getString("end_cep"),rs.getString("end_estado"),
               rs.getString("end_cidade"),rs.getInt("end_numero"),rs.getString("end_logradouro"));
               
               endereco.setId(rs.getInt("end_id"));
               endereco.setDtcadastro(rs.getDate("end_dtcadastro"));
               
               enderecos.add(endereco);
            }
            
            return enderecos;
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
