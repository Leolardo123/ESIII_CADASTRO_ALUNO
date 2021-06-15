/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import static Dao.AbstractDAO.conexao;
import Dominio.Endereco;
import Dominio.EntidadeDominio;
import Dominio.Materia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eu
 */
public class DAODependentes extends AbstractDAO {

    public DAODependentes() {
        table = "dependentes";
        prefixo = "dep_";
        id_table = prefixo + "dependencia_id";
    }

    //concluido - falta testar
    @Override
    public void salvar(EntidadeDominio entidade) {//Espera receber uma entidade de uma matéria!

        try {
            openConnection();
            Materia materia = (Materia) entidade;

            conexao.setAutoCommit(false);

            if (materia.getDependencias() != null) {
                for (Materia dependencia : materia.getDependencias()) {
                    StringBuilder sql = new StringBuilder();
                    sql.append("INSERT INTO " + table + "(dep_materia_id, dep_dependencia_id)");
                    sql.append(" VALUES (?,?)");

                    pst = conexao.prepareStatement(sql.toString());
                    pst.setInt(1, materia.getId());
                    pst.setInt(2, dependencia.getId());
                    pst.executeUpdate();
                }
            }

        } catch (SQLException e) {
            try {
                System.out.println("Erro na inserção: " + e);
                conexao.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
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

    public void alterar(EntidadeDominio entidade) {//Nunca usado

        try {
            openConnection();
            Materia materia = (Materia) entidade;

            conexao.setAutoCommit(false);

            if (materia.getDependencias() != null) {
                for (Materia dependencia : materia.getDependencias()) {
                    StringBuilder sql = new StringBuilder();
                    sql.append("UPDATE " + table + " SET dep_dependencia_id = ? WHERE dep_materia_id = ? ");
                    pst = conexao.prepareStatement(sql.toString());
                    pst.setInt(1, dependencia.getId());
                    pst.setInt(2, materia.getId());
                    pst.executeUpdate();
                }
            }

            System.out.println("alterado com sucesso");
        } catch (SQLException e) {
            try {
                System.out.println("Erro na alteração: " + e);
                conexao.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
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

    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        Materia materia = (Materia)entidade;
        
        try {
            openConnection();

            StringBuilder sql = new StringBuilder();
            if (entidade == null || entidade.getId() == 0) {
                sql.append("SELECT * FROM " + table + " LEFT JOIN materias ");
                sql.append("ON dep_dependencia_id = mat_id");
            } else {
                sql.append("SELECT * FROM " + table + " LEFT JOIN materias ");
                sql.append("ON dep_dependencia_id = mat_id  WHERE dep_materia_id = " + entidade.getId());
            }

            pst = conexao.prepareStatement(sql.toString());
            ResultSet rs = pst.executeQuery();

            List<EntidadeDominio> dependencias = new ArrayList<EntidadeDominio>();

            while (rs.next()) {
                Materia dependencia = new Materia(rs.getString("mat_nome"), rs.getString("mat_descricao"),
                        rs.getInt("mat_carga_horaria"));

                dependencia.setId(rs.getInt("mat_id"));
                dependencia.setDtcadastro(rs.getDate("mat_dtcadastro"));

                dependencias.add(dependencia);
            }

            return dependencias;
        } catch (SQLException e) {
            try {
                System.out.println("Erro ao recuperar: " + e);
                conexao.rollback();
            } catch (SQLException e1) {
            }
            e.printStackTrace();
        } finally {
            try {
                if(this.ctrlTransaction){
                    closeConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<EntidadeDominio> consultarTodos(EntidadeDominio entidade) {//retorna todas as dependencias diretas e indiretas da materia selecionada, também quem depende da materia
        //Esse método serve para ajudar a validar a lógica de dependencias de uma determinada materia
        Materia materia = (Materia) entidade;
        if (materia.getId() == 0) {
            return null;
        }
        try {
            openConnection();

            conexao.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();

            if (entidade != null || entidade.getId() != 0) {
                sql.append("SELECT DISTINCT(mat_id),mat_nome,mat_descricao,mat_carga_horaria,mat_dtcadastro,nivel FROM(");
                sql.append("WITH RECURSIVE sub_dep AS(");
                sql.append(" SELECT mat_nome nome_materia,mat_id id_materia,dep_dependencia_id id_dependencia, 1 AS nivel FROM dependentes");
                sql.append(" LEFT JOIN materias ON dep_materia_id = mat_id WHERE mat_id = ");
                sql.append(materia.getId());
                sql.append(" UNION ALL");
                sql.append(" SELECT m.mat_nome,m.mat_id,d.dep_dependencia_id, sub_dep.nivel+1 FROM dependentes d LEFT JOIN materias m ON dep_materia_id = mat_id");
                sql.append(" INNER JOIN sub_dep ON sub_dep.id_dependencia = d.dep_materia_id");
                sql.append(")");
                sql.append(" SELECT id_materia,id_dependencia,nivel FROM sub_dep  ");
                sql.append(" GROUP BY id_materia,id_dependencia,nivel) all_deps");
                sql.append(" LEFT JOIN materias ON mat_id = all_deps.id_dependencia");
            } else {
                return null;
            }

            pst = conexao.prepareStatement(sql.toString());
            ResultSet rs = pst.executeQuery();

            List<EntidadeDominio> dependencias = new ArrayList<EntidadeDominio>();

            while (rs.next()) {
                Materia dependencia = new Materia(rs.getString("mat_nome"), rs.getString("mat_descricao"),
                        rs.getInt("mat_carga_horaria"));
                
                dependencia.setId(rs.getInt("mat_id"));
                dependencia.setDtcadastro(rs.getDate("mat_dtcadastro"));

                if (dependencia.getId() == materia.getId()) {
                    dependencias = new ArrayList<EntidadeDominio>();
                    dependencias.add(dependencia);
                    return dependencias;
                }else
                if (!(dependencias.contains(dependencia))) {
                    dependencias.add(dependencia);
                }
            }

            return dependencias;
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
        Materia materia = (Materia)entidade;
        try {
            openConnection();

            conexao.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();

            for(Materia dependencia:materia.getDependencias()){
                sql.append(" DELETE FROM " + table + " WHERE ");
                sql.append(" dep_materia_id ");
                sql.append(" = ");
                sql.append(materia.getId());
                sql.append(" AND ");
                sql.append(" dep_dependencia_id ");
                sql.append(" = ");
                sql.append(dependencia.getId());
                pst = conexao.prepareStatement(sql.toString());
                pst.executeUpdate();
            }

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
    }
}


