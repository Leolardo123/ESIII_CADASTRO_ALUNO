/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.*;
import Dominio.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Eu
 */
public class Fachada implements IFachada {

    private Map<String, IDAO> daos;

    //private Map<String, List<IStrategy>> rns;

    public Fachada() {
        definirDAOS();
        //definirRNS();

    }

    // Strategy a ser implementado
    private void definirRNS() {}

    private void definirDAOS() {
        daos = new HashMap<String, IDAO>();
        daos.put(Aluno.class.getName(), new DAOAluno());
        daos.put(Professor.class.getName(), new DAOProfessor());
        daos.put(Endereco.class.getName(), new DAOEndereco());
        daos.put(Curso.class.getName(), new DAOCurso());
        daos.put(Materia.class.getName(), new DAOMateria());
        daos.put(GradeCurso.class.getName(), new DAOGrade());
    }

    // Strategy a ser implementado
    @Override
    public String cadastrar(EntidadeDominio entidade) {
        return null;
    }

    // Strategy a ser implementado
	private String executarRegras(EntidadeDominio entidade) {      
            return null;
	}
        
    @Override
    public String excluir(EntidadeDominio entidade) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String alterar(EntidadeDominio entidade) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        // TODO Auto-generated method stub
        return null;
    }

}
