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
     * Test of salvar method, of class DAOMateria.
     */
    @Test
    public void testSalvar() {
        Materia dependencia = new Materia("Calculo I", "CAL1", 120);
        
        DAOMateria dao = new DAOMateria();
        dao.salvar(dependencia);
        
        List<Materia> dependencias = new ArrayList<Materia>();
        dependencias.add(dependencia);
        
        Materia materia = new Materia("Matemática básica", "MAB", 80,dependencias);
   
        dao.salvar(materia);
    }
    
    @Test
    public void testConsultar() {
        DAOMateria dao = new DAOMateria();
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
        Materia materia;
    
        int id = 1;
        
        List<EntidadeDominio> entidadesMaterias = dao.consultar(id);
        
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
        
        DAOMateria dao = new DAOMateria();
        dao.excluir(materia);
    }
}
