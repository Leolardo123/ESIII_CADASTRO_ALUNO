/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dominio.Materia;
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
        Materia materia = new Materia("Administração", "adm", 120, 1);
        
        DAOMateria dao = new DAOMateria();
        dao.salvar(materia);
    }

    @Test
    public void testExcluir() {
        Materia materia = new Materia("Administração", "adm", 120, 1);
        materia.setId(1);
        
        DAOMateria dao = new DAOMateria();
        dao.excluir(materia);
    }
    
}
