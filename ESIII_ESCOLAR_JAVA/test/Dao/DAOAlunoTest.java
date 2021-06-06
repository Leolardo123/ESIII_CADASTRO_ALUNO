package Dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Dominio.Aluno;
import Dominio.Curso;
import Dominio.Endereco;
import Dominio.EntidadeDominio;
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
public class DAOAlunoTest {
    
    public DAOAlunoTest() {
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
     * Test of salvar method, of class DAOAluno.
     * @throws java.text.ParseException
     */
    @Test
    public void testSalvar() throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formato.parse("25/12/1967");
        Endereco endereco = new Endereco("08990320", "SP","Moji das Cruzes",100,"Rua Leonardo Fabricio Lopes");
        Curso cur = new Curso();
        cur.setId(1);
        Aluno aluno = new Aluno("545675677", "45645644411", "Leonardo", 
            "Takeshi", "leo_takeshi@gmail.com", data, endereco,
            1 , cur);
        
        DAOAluno DAOalu = new DAOAluno();
        DAOalu.salvar(aluno);
    }
    
    @Test
    public void testAlterar() throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formato.parse("25/12/1967");
        Curso cur = new Curso();
        cur.setId(1);
        Endereco endereco = new Endereco("08990320", "SP","Moji das Cruzes",100,"Rua Leonardo Fabricio Lopes");
        Aluno aluno = new Aluno("545675677", "45645644411", "Ldwdwrdo", 
            "Takeshi", "leo_takeshi@gmail.com", data, endereco,
            1 , cur);
        
        
        data = formato.parse("25/12/1967");
        endereco = new Endereco("084123111", "MG","Sei la",100,"Rua Spinner Splaining");
        aluno = new Aluno("545675677", "45645444411", "Takeshi", 
            "Lopez", "jose@gmail.com", data, endereco,
            1 , cur);
        
        DAOAluno DAOalu = new DAOAluno();
        DAOalu.alterar(aluno);
    }
    
    @Test
    public void testExcluir() throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formato.parse("25/12/1967");
        Endereco endereco = new Endereco("08440111", "MG","Sei la",100,"Rua Spinner Splaining");
        Curso cur = new Curso();
        cur.setId(1);
        Aluno aluno = new Aluno("545675677", "45645644411", "Takeshi", 
            "Lopez", "jose@gmail.com", data, endereco,
            1 , cur);
        
        DAOAluno DAOalu = new DAOAluno();
        DAOalu.excluir(aluno);
    }
    
    @Test
    public void testConsultar() throws ParseException {
        Aluno aluno = new Aluno();
        
        DAOAluno dao = new DAOAluno();
        List<EntidadeDominio> EntidadesAluno = dao.consultar(null);
        System.out.println(    "---------------------------------------------------------------------------");
        for(int i=0;i<EntidadesAluno.size();i++){
            aluno  = (Aluno)EntidadesAluno.get(i);
            
            System.out.println("Aluno----------");
            System.out.println("Semestre:"+aluno .getSemestre());
            System.out.println("Curso:"+aluno.getCurso().getNome());
            System.out.println("Pessoa-------------");
            System.out.println("RG:"+aluno.getRg()+"\nCPF:\"+"+aluno.getCpf()+"\n"+aluno.getId()+"\n"
                +aluno.getPnome()+" "+aluno.getUnome()+"\n"+aluno.getDtNascimento());
            System.out.println("---------------------------------------------------------------------------");
        }
    }
    
    @Test
    public void testConsultarId() throws ParseException {
        Aluno aluno = new Aluno();
        aluno.setId(1);
        
        DAOAluno dao = new DAOAluno();
        List<EntidadeDominio> EntidadesAluno = dao.consultar(aluno);
        System.out.println(    "---------------------------------------------------------------------------");
        for(int i=0;i<EntidadesAluno.size();i++){
            aluno  = (Aluno)EntidadesAluno.get(i);
            
            System.out.println("Aluno----------");
            System.out.println("Semestre:"+aluno .getSemestre());
            System.out.println("Curso:"+aluno.getCurso().getNome());
            System.out.println("Pessoa-------------");
            System.out.println("RG:"+aluno.getRg()+"\nCPF:\"+"+aluno.getCpf()+"\n"+aluno.getId()+"\n"
                +aluno.getPnome()+" "+aluno.getUnome()+"\n"+aluno.getDtNascimento());
            System.out.println("---------------------------------------------------------------------------");
        }
    }
}
