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
import Dominio.Professor;
import java.sql.SQLException;

/**
 *
 * @author Eu
 */
public class DAOProfessor extends AbstractDAO{
    public DAOProfessor(){}
    
    public void salvar(EntidadeDominio entidade) {
        Professor professor = (Professor)entidade;
        Pessoa pessoa = (Pessoa)entidade;
        Endereco endereco = pessoa.getEndereco();

        try {
                conexao.setAutoCommit(false);	
                DAOEndereco DAOend = new DAOEndereco();
                DAOend.ctrlTransaction = false;
                DAOend.salvar(endereco);
                DAOPessoa DAOpes = new DAOPessoa();
                DAOpes.salvar(pessoa);

                StringBuilder sql = new StringBuilder();
                sql.append("INSERT INTO Professor(pro_salario, pro_pes_id)");
                sql.append(" VALUES (?,?)");		

                pst = conexao.prepareStatement(sql.toString());
                pst.setDouble(1, professor.getSalario());
                pst.setInt(2, professor.getId());
                pst.executeUpdate();			
                conexao.commit();		
        } catch (SQLException e) {
                try {
                        conexao.rollback();
                } catch (SQLException e1) {
                        e1.printStackTrace();
                }
                e.printStackTrace();			
        }finally{
                try {
                    closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
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
