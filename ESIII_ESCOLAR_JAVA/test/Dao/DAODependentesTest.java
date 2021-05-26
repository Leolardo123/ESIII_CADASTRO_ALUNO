/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dominio.EntidadeDominio;
import Dominio.Materia;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author 55119
 */
public class DAODependentesTest {
     public DAODependentesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of salvar method, of class DAODependentes.
     */
    @Test
    public void testSalvar() {
        Materia dependencia = new Materia("Matemática básica", "MAB", 80);
               
        DAOMateria dao = new DAOMateria();
        dao.ctrlTransaction = false;//Impede o fechamento prematuro da conexão
        dao.salvar(dependencia);
        
        List<Materia> dependencias = new ArrayList<Materia>();
        dependencias.add(dependencia);
        
        Materia materia1 = new Materia("Calculo I", "CAL1", 1200,dependencias);
        dao.salvar(materia1);
        
        Materia materia2 = new Materia("Fisica I", "FIS1", 1200,dependencias);
        dao.salvar(materia2);
        
        Materia materia3 = new Materia("Estatistica I", "EST1", 1200,dependencias);
        dao.salvar(materia3);
        
        dependencias = new ArrayList<Materia>();
        dependencias.add(materia1);//calculo 1
        
        Materia materia4 = new Materia("Calculo II", "CAL2", 800,dependencias);
        dao.salvar(materia4);
        
        dependencias = new ArrayList<Materia>();
        dependencias.add(materia2);//Fisica 1
        dependencias.add(materia3);//Estatistica 1
        dependencias.add(materia4);//Calculo 2
        
        Materia materia5 = new Materia("Resistência dos Materiais I", "RESMAT1", 3600,dependencias);
        
        dao.ctrlTransaction = true;//autoriza o fechamento da conexão
        
        dao.salvar(materia5);
        
        //DEPENDENCIAS:
        //RESMAT -> EST1,FIS1,(CAL2->CAL1)
    }
    
    @Test
    public void testConsultar() {
        DAODependentes dao = new DAODependentes();
        Materia materia;
        
        List<EntidadeDominio> entidadesMaterias = dao.consultar();
        
        for(EntidadeDominio entidade: entidadesMaterias){
            materia = (Materia)entidade;
            
            System.out.println("---------------------------------------------------");
            System.out.println(materia.getNome()+"\n"+materia.getDescricao()+
                               "\n"+materia.getCarga_horaria()+"\n"+materia.getId()+"\n"+materia.getDtcadastro()+"\n");
            
            System.out.println("---------------------------------------------------");
        }
    }
    
    @Test
    public void testConsultarId() {
        DAODependentes dao = new DAODependentes();
        Materia materia = new Materia();
        materia.setId(1);
        
        List<EntidadeDominio> entidadesMaterias = dao.consultar(materia);
        
        for(EntidadeDominio entidade: entidadesMaterias){
            materia = (Materia)entidade;
            System.out.println("---------------------------------------------------");
            System.out.println("ID");
            System.out.println("---------------------------------------------------");
            System.out.println(materia.getNome()+"\n"+materia.getDescricao()+
                               "\n"+materia.getCarga_horaria()+"\n"+materia.getId()+"\n"+materia.getDtcadastro()+"\n");
            
            System.out.println("---------------------------------------------------");
        }
    }

    @Test
    public void testExcluir() {
        Materia materia = new Materia("Administração", "adm", 120);
        materia.setId(1);
        
        DAODependentes dao = new DAODependentes();
        dao.excluir(materia);
    }
}
