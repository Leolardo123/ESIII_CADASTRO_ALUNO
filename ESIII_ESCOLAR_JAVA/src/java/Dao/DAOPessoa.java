/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import static Dao.AbstractDAO.conexao;
import Dominio.Endereco;
import Dominio.EntidadeDominio;
import Dominio.Pessoa;
import java.sql.Date;
import java.sql.SQLException;
/**
 *
 * @author Eu
 */
public class DAOPessoa extends AbstractDAO {

    public DAOPessoa() {
    }

    //concluido - falta testar
    @Override
    public void salvar(EntidadeDominio entidade) {
        Pessoa pessoa = (Pessoa) entidade;
        Endereco endereco = pessoa.getEndereco();

        try {
            openConnection();
            DAOEndereco DAOend = new DAOEndereco();
            DAOend.ctrlTransaction = false;
            DAOend.salvar(endereco);

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO pessoas(pes_pnome, pes_unome, pes_cpf, pes_rg, ");
            sql.append("pes_email, pes_dtnascimento, pes_end_id)");
            sql.append(" VALUES (?,?,?,?,?,?,?)");

            pst = conexao.prepareStatement(sql.toString());
            pst.setString(1, pessoa.getPnome());
            pst.setString(2, pessoa.getUnome());
            pst.setString(3, pessoa.getCpf());
            pst.setString(4, pessoa.getRg());
            pst.setString(5, pessoa.getEmail());
            pst.setDate(6, (Date) pessoa.getDtNascimento());
            pst.setInt(7, pessoa.getEndereco().getId());
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
