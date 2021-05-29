/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dominio.EntidadeDominio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import regrasNegocio.implRegras.GetTabela;
/**
 *
 * @author Eu
 */
public abstract class AbstractDAO implements IDAO {

    protected static Connection conexao = null;
    protected static PreparedStatement pst = null;
    protected static ResultSet rs = null;
    protected boolean ctrlTransaction = true;
    protected String table;
    protected String id_table;
    protected String prefixo;

    protected void conectar() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ESIII_ESCOLAR",
                 "postgres", "123");
        conexao = conn;
    }

    protected void openConnection() {
        try {
            if (conexao == null || conexao.isClosed()) {
                conectar();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<String> getColunas(EntidadeDominio entidade){
        ArrayList<String> colunas = new ArrayList<String>();
        GetTabela getTabela = new GetTabela();
        String tb_name = getTabela.processar(entidade);
        if (tb_name != null) {
            try {
                pst = conexao.prepareStatement("SELECT * FROM " + tb_name);
                ResultSet rs = pst.executeQuery();

                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();

                for (int i = 1; i < columnCount; i++) {
                    String columnName = metadata.getColumnName(i);
                    colunas.add(columnName);
                }
            } catch (SQLException e) {
                try {
                    System.out.println("Erro ao selecionar colunas: " + e);
                    conexao.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            } finally {
                try {
                    pst.close();
                    if (ctrlTransaction) {
                        conexao.close();
                    }
                    return colunas;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    protected static void closeConnection() throws SQLException {
        if (conexao != null) {
            conexao.close();
        }
        if (pst != null) {
            pst.close();
        }
        if (rs != null) {
            rs.close();
        }
    }

    @Override
    public void excluir(EntidadeDominio entidade) {
        openConnection();
        pst = null;
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ");
        sql.append(table);
        sql.append(" WHERE ");
        sql.append(id_table);
        sql.append("=");
        sql.append("?");
        try {
            conexao.setAutoCommit(false);
            pst = conexao.prepareStatement(sql.toString());
            pst.setInt(1, entidade.getId());

            pst.executeUpdate();
            conexao.commit();
            System.out.println("Excluido com sucesso");
        } catch (SQLException e) {
            try {
                System.out.println("Erro ao excluir: "+e);
                conexao.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {

            try {
                pst.close();
                if (ctrlTransaction) {
                    conexao.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
