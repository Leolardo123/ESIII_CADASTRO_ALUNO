/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dominio.Endereco;
import Dominio.EntidadeDominio;
import Dominio.Pessoa;
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
 * @author 55119
 */
public class DAOPessoaTest {
            
    public DAOPessoaTest() {
        
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
        Pessoa pessoa = new Pessoa("545615677", "45635644411", "Leonardo", 
            "Takeshi", "leo_takeshi@gmail.com", data, endereco);
        
        DAOPessoa DAOpes = new DAOPessoa();
        DAOpes.salvar(pessoa);
    }
//    @Test
//    public void testAlterar() throws ParseException {
//        Endereco endereco = new Endereco("08440111", "MG","Sei la",100,"Rua Spinner Splaining");
//        
//        DAOEndereco DAOend = new DAOEndereco();
//        DAOend.alterar(endereco);
//    }
    @Test
    public void testConsultar() throws ParseException {
        Pessoa pessoa = new Pessoa(); 
        
        DAOPessoa DAOpes = new DAOPessoa();
        List<EntidadeDominio> EntidadesPessoa = DAOpes.consultar();
        System.out.println("---------------------------------------------------------------------------");
        for(int i=0;i<EntidadesPessoa.size();i++){
            pessoa = (Pessoa)EntidadesPessoa.get(i);
            System.out.println(pessoa.getRg()+"\n"+pessoa.getCpf()+"\n"+pessoa.getId()+"\n"
                +pessoa.getPnome()+"\n"+pessoa.getUnome()+"\n"+pessoa.getDtNascimento());
            System.out.println("---------------------------------------------------------------------------");
        }
    }
    
    @Test
    public void testConsultarId() throws ParseException {
        int id = 1;
        
        Pessoa pessoa = new Pessoa(); 
        
        DAOPessoa DAOend = new DAOPessoa();
        List<EntidadeDominio> EntidadesPessoa = DAOend.consultar(id);
        
        for(int i=0;i<EntidadesPessoa.size();i++){
            pessoa = (Pessoa)EntidadesPessoa.get(i);
            System.out.println(pessoa.getRg()+"\n"+pessoa.getCpf()+"\n"+pessoa.getId()+"\n"
                +pessoa.getPnome()+"\n"+pessoa.getUnome()+"\n"+pessoa.getDtNascimento());
            System.out.println("id---------------------------------------------------------------------------");
        }
    }
    
    @Test
    public void testExcluir() throws ParseException {;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formato.parse("25/12/1967");
        Endereco endereco = new Endereco("08990320", "SP","Moji das Cruzes",100,"Rua Leonardo Fabricio Lopes");
        Pessoa pessoa = new Pessoa("545675677", "45645644411", "Leonardo", 
            "Takeshi", "leo_takeshi@gmail.com", data, endereco);
        
        pessoa.setId(3);
        
        DAOPessoa DAOend = new DAOPessoa();
        DAOend.excluir(endereco);
    };
}
