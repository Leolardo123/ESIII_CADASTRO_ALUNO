package Dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Dominio.Endereco;
import Dominio.Professor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 *
 * @author Eu
 */
public class DAOProfessorTest {
    
    public DAOProfessorTest() {
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
     * Test of salvar method, of class DAOProfessor.
     * @throws java.text.ParseException
     */
    @Test
    public void testSalvar() throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date data = formato.parse("25-12-1967");
        Endereco endereco = new Endereco("08990320", "SP","Moji das Cruzes",100,"Rua Leonardo Fabricio Lopes");
        Professor professor = new Professor(1500.00,"123675677", "45644411", "Leonardo", 
            "Takeshi", "leo_takeshi@gmail.com", data, endereco);
        
        DAOProfessor DAOpro = new DAOProfessor();
        DAOpro.salvar(professor);
    }
    
    @Test
    public void testExcluir() throws ParseException{;
                SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date data = formato.parse("25-12-1967");
        Endereco endereco = new Endereco("08990320", "SP","Moji das Cruzes",100,"Rua Leonardo Fabricio Lopes");
        Professor professor = new Professor(1500.00,"123675677", "45644411", "Leonardo", 
            "Takeshi", "leo_takeshi@gmail.com", data, endereco);
        
        DAOProfessor DAOpro = new DAOProfessor();
        DAOpro.excluir(professor);
    }
}
