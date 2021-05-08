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
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eu
 */
public class DAOPessoa extends AbstractDAO {

    public DAOPessoa() {
    }

    @Override
    public void salvar(EntidadeDominio entidade) {
        Pessoa pessoa = (Pessoa) entidade;
        Endereco endereco = pessoa.getEndereco();

        try {
            if(conexao == null) openConnection();
            DAOEndereco DAOend = new DAOEndereco();
            DAOend.ctrlTransaction = false;
            DAOend.salvar(endereco);

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO Pessoas(pes_pnome, pes_unome, pes_cpf, pes_rg, ");
            sql.append("pes_email, pes_dtnascimento, pes_dtcadastro, pes_end_id)");
            sql.append(" VALUES (?,?,?,?,?,?,?,?)");

            pst = conexao.prepareStatement(sql.toString());
            pst.setString(1, pessoa.getPnome());
            pst.setString(2, pessoa.getUnome());
            pst.setString(3, pessoa.getCpf());
            pst.setString(4, pessoa.getRg());
            pst.setString(5, pessoa.getEmail());
            pst.setDate(6, (Date) pessoa.getDtNascimento());
            Timestamp time = new Timestamp(pessoa.getDtcadastro().getTime());
            pst.setTimestamp(7, time);
            pst.setInt(8, pessoa.getEndereco().getId());
            pst.executeUpdate();
            conexao.commit();					
        } catch (SQLException e) {
                try {
                        conexao.rollback();
                } catch (SQLException e1) {
                        e1.printStackTrace();
                }
                e.printStackTrace();	
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOEndereco.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ctrlTransaction){
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
