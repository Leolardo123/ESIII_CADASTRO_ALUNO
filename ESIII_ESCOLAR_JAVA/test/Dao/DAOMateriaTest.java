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
 * @author Eu
 */
public class DAOMateriaTest {
    
    public DAOMateriaTest() {
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
        Materia materia = new Materia("Administração", "ADM", 120);
        
        DAOMateria dao = new DAOMateria();
        dao.salvar(materia);
        
        materia = new Materia("Matemagica", "MAT", 120);
        
        dao.salvar(materia);
    }
    
    @Test
    public void testConsultar() {
        DAOMateria dao = new DAOMateria();
        Materia materia;
        
        List<EntidadeDominio> entidadesMaterias = dao.consultar();
        
        for(EntidadeDominio entidade: entidadesMaterias){
            materia = (Materia)entidade;
            
            System.out.println(materia.getNome()+"\n"+materia.getDescricao()+
                               "\n"+materia.getCarga_horaria()+"\n"+materia.getId()+"\n"+materia.getDtcadastro()+"\n");
            
            if(materia.getDependencias().size()>0){//Exibe nome das dependencias
                List<Materia> dependencias = materia.getDependencias();
                
                System.out.println("---Dependencias---");
                for(Materia dependencia: dependencias){
                    System.out.println(dependencia.getNome()+":"+dependencia.getId());
                }
            }
            System.out.println("---------------------------------------------------");
        }
    }
    
//    @Test
    public void testConsultarId() {
        DAOMateria dao = new DAOMateria();
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
            
            if(materia.getDependencias().size()>0){//Exibe nome das dependencias
                List<Materia> dependencias = materia.getDependencias();
                
                System.out.println("---Dependencias---");
                for(Materia dependencia: dependencias){
                    System.out.println(dependencia.getNome()+":"+dependencia.getId());
                }
            }
            System.out.println("---------------------------------------------------");
        }
    }

//    @Test
    public void testExcluir() {
        Materia materia = new Materia("Administração", "adm", 120);
        materia.setId(1);
        
        DAOMateria dao = new DAOMateria();
        dao.excluir(materia);
    }
    
}
