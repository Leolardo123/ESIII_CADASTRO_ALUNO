/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dominio.EntidadeDominio;
import Dominio.GradeCurso;
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
public class DAOGradeTest {
    
    public DAOGradeTest() {
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
     * Test of salvar method, of class DAOGrade.
     */
    @Test
    public void testSalvar() {
        GradeCurso grade = new GradeCurso( true, 1, 0, 1, 0, 0 ,0);
        
        DAOGrade dao = new DAOGrade();
        dao.salvar(grade);
    }
    
    @Test
    public void testConsultar() {
        DAOGrade dao = new DAOGrade();
        
        List<EntidadeDominio> entidadeGrade = dao.consultar();
        
        
        for(EntidadeDominio entidade: entidadeGrade){
            GradeCurso grade = (GradeCurso)entidade;
            
            System.out.println("----------------------------------------");
            System.out.println(grade.isObrigatorio()+"\n"+grade.getDia_semana()+"\n"+grade.getTurno()+"\n"
                    +grade.getPeriodo()+"\n"+grade.getCurso_id()+"\n"+grade.getMateria_id()+"\n"+grade.getProfessor_id());
        }
    }
    
    @Test
    public void testConsultarId() {
        
    }
    
    
    @Test
    public void testExcluir() {
        GradeCurso grade = new GradeCurso( true, 1, 0, 1, 0, 0 ,0);
        grade.setId(1);
        
        DAOGrade dao = new DAOGrade();
        dao.excluir(grade);
    }
    
}
