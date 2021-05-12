/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dominio.GradeCurso;
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
    public void testExcluir() {
        GradeCurso grade = new GradeCurso( true, 1, 0, 1, 0, 0 ,0);
        grade.setId(1);
        
        DAOGrade dao = new DAOGrade();
        dao.excluir(grade);
    }
    
}
