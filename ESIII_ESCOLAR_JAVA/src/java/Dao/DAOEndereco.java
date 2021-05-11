/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dominio.Endereco;
import Dominio.EntidadeDominio;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Eu
 */
public class DAOEndereco extends AbstractDAO {

    public DAOEndereco() {
    }

    //concluido - falta testar
    @Override
    public void salvar(EntidadeDominio entidade) {
        try {
            openConnection();
            Endereco end = (Endereco) entidade;
            StringBuilder sql = new StringBuilder();

            sql.append("INSERT INTO enderecos(end_cep, end_estado, end_cidade, end_numero, ");
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
                idEnd = rs.getInt(1);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void consultar(EntidadeDominio entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void excluir(EntidadeDominio entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
