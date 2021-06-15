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
import regrasNegocio.IStrategy;
import regrasNegocio.implRegras.ValidarAluno;
import regrasNegocio.implRegras.ValidarCPF;
import regrasNegocio.implRegras.ValidarCurso;
import regrasNegocio.implRegras.ValidarMateria;
import regrasNegocio.implRegras.ValidarPessoa;
import regrasNegocio.implRegras.ValidarProfessor;
import regrasNegocio.implRegras.ValidarRG;

/**
 *
 * @author Eu
 */
public class Fachada implements IFachada {

    private Map<String, IDAO> daos;

    private Map<String, List<IStrategy>> rns;

    public Fachada() {
        definirDAOS();
        definirRNS();
    }

    // Strategy a ser implementado
    private void definirRNS() {
        rns = new HashMap<String, List<IStrategy>>();
        
        ValidarAluno valAluno = new ValidarAluno();
        ValidarProfessor valProfessor = new ValidarProfessor();
        ValidarCurso valCurso = new ValidarCurso();
        ValidarMateria valMateria = new ValidarMateria();
        ValidarCPF valCPF = new ValidarCPF();
        ValidarRG valRG = new ValidarRG();
        
        List<IStrategy> rnsAluno = new ArrayList<IStrategy>(); 
        //+val
        rnsAluno.add(valAluno);
        
        List<IStrategy> rnsProfessor = new ArrayList<IStrategy>(); 
        //+val
        rnsProfessor.add(valProfessor);
        
        List<IStrategy> rnsCurso = new ArrayList<IStrategy>(); 
        //+val
        rnsCurso.add(valCurso);
        
        List<IStrategy> rnsMateria = new ArrayList<IStrategy>(); 
        //+val
        rnsMateria.add(valMateria);
        
        rns.put(Aluno.class.getName(),     rnsAluno);
        rns.put(Professor.class.getName(), rnsProfessor);
        rns.put(Curso.class.getName(),     rnsCurso);
        rns.put(Materia.class.getName(),   rnsMateria);
    }

    private void definirDAOS() {
        daos = new HashMap<String, IDAO>();
        daos.put(Aluno.class.getName(),    new DAOAluno());
        daos.put(Professor.class.getName(),new DAOProfessor());
        daos.put(Endereco.class.getName(), new DAOEndereco());
        daos.put(Curso.class.getName(),    new DAOCurso());
        daos.put(Materia.class.getName(),  new DAOMateria());
    }

    // Strategy a ser implementado
    @Override
    public String cadastrar(EntidadeDominio entidade) {
        String nmClasse = entidade.getClass().getName();
        String msg = executarRegras(entidade);
        if (entidade.getId()!=0){//verifica se as validações encontraram um item já existente
            nmClasse.replace("Dominio.","");
            return msg+nmClasse+" já existe!";
        }
        if (msg == null) {
            IDAO dao = daos.get(nmClasse);
            dao.salvar(entidade);
        } else {
            return msg;
        }
        return null;
    }

    // Strategy a ser implementado
    private String executarRegras(EntidadeDominio entidade) {
        String nmClasse = entidade.getClass().getName();
        StringBuilder msg = new StringBuilder();

        List<IStrategy> regras = rns.get(nmClasse);

        if (regras != null) {
            for (IStrategy s : regras) {
                String m = s.processar(entidade);

                if (m != null) {
                    msg.append(m);
                    msg.append("\n");
                }
            }
        }

        if (msg.length() > 0) {
            return msg.toString();
        } else {
            return null;
        }
    }

    @Override
    public String excluir(EntidadeDominio entidade) {
        String nmClasse = entidade.getClass().getName();
        IDAO dao = daos.get(nmClasse);
        dao.excluir(entidade);
        return null;
    }

    @Override
    public String alterar(EntidadeDominio entidade) {
        String nmClasse = entidade.getClass().getName();
        String msg = executarRegras(entidade);
        if (msg == null) {
            IDAO dao = daos.get(nmClasse);
            dao.alterar(entidade);
        } else {
            return msg;
        }
        return null;
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        List<EntidadeDominio> lista_entidade = null;
        String nmClasse = entidade.getClass().getName();
        System.out.println(nmClasse);
        IDAO dao = daos.get(nmClasse);
        lista_entidade = dao.consultar(entidade);
        return lista_entidade;
    }
    
    @Override
    public List<EntidadeDominio> consultarTodasDep() {
        //método que chama consulta recursiva que acha todas as dependencias e suas subdependencias de todas as materias
        DAOMateria DAOmat = new DAOMateria();
        DAODependentes DAOdep = new DAODependentes();
        
        List<EntidadeDominio> listaMaterias = DAOmat.consultar(null);
        if(listaMaterias!=null){
            for(EntidadeDominio entidadeMateria:listaMaterias){
                List<Materia> dependencias = new ArrayList<Materia>();
                List<EntidadeDominio> listaDependencias = new ArrayList<EntidadeDominio>();
                listaDependencias = DAOdep.consultarTodos((Materia)entidadeMateria);
                if(listaDependencias!=null){
                    for(EntidadeDominio entidade:listaDependencias){
                        dependencias.add((Materia)entidade);
                    }
                }
                ((Materia)entidadeMateria).setDependencias(dependencias);
        }
        }
        return listaMaterias;
    }
}
