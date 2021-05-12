  
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dominio.Aluno;
import Dominio.EntidadeDominio;
import Dominio.Pessoa;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Eu
 */
public class DAOAluno extends AbstractDAO {

    public DAOAluno() {
        table = "alunos";
        id_table = "alu_pes_id";
    }

    //concluido - falta testar
    @Override
    public void salvar(EntidadeDominio entidade) {
        Aluno aluno = (Aluno) entidade;
        Pessoa pessoa = (Pessoa) entidade;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}