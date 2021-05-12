package Dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Dominio.Curso;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 *
 * @author Eu
 */
public class DAOCursoTest {
    
    public DAOCursoTest() {
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
     * Test of salvar method, of class DAOCurso.
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @Test
    public void testSalvar(){;
        Curso curso;
        curso = new Curso("Administração", "Curso de TI","Tecnologo",48, 240.00);

        DAOCurso dao = new DAOCurso();
        dao.salvar(curso);
    }
    
    @Test
    public void testExcluir(){;
        Curso curso;
        curso = new Curso("Administração", "Curso de TI","Tecnologo",48, 240.00);
        curso.setId(1);
        
        DAOCurso dao = new DAOCurso();
        dao.excluir(curso);
    }
    
}
