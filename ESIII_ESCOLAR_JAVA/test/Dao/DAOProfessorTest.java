package Dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Dominio.Endereco;
import Dominio.EntidadeDominio;
import Dominio.Pessoa;
import Dominio.Professor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public void testConsultar() throws ParseException {
        Professor professor = new Professor();
        
        DAOProfessor dao = new DAOProfessor();
        List<EntidadeDominio> EntidadesProfessor = dao.consultar(null);
        System.out.println("---------------------------------------------------------------------------");
        for(int i=0;i<EntidadesProfessor.size();i++){
            professor  = (Professor)EntidadesProfessor.get(i);
            
            System.out.println("Professor----------");
            System.out.println("Salario:"+professor .getSalario());
            System.out.println("Pessoa-------------");
            System.out.println(professor.getRg()+"\n"+professor.getCpf()+"\n"+professor.getId()+"\n"
                +professor.getPnome()+"\n"+professor.getUnome()+"\n"+professor.getDtNascimento());
            System.out.println("---------------------------------------------------------------------------");
        }
    }
    
    @Test
    public void testConsultarId() throws ParseException {
        Professor professor = new Professor();
        
        professor.setId(1);
        
        DAOProfessor dao = new DAOProfessor();
        List<EntidadeDominio> EntidadesProfessor = dao.consultar(professor);
        System.out.println(    "---------------------------------------------------------------------------");
        for(int i=0;i<EntidadesProfessor.size();i++){
            professor  = (Professor)EntidadesProfessor.get(i);
            
            System.out.println("Professor----------");
            System.out.println("Salario:"+professor .getSalario());
            System.out.println("Pessoa-------------");
            System.out.println(professor.getRg()+"\n"+professor.getCpf()+"\n"+professor.getId()+"\n"
                +professor.getPnome()+"\n"+professor.getUnome()+"\n"+professor.getDtNascimento());
            System.out.println("---------------------------------------------------------------------------");
        }
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
