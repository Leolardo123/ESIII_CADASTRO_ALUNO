/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dominio.Endereco;
import Dominio.EntidadeDominio;
import java.text.ParseException;
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
public class DAOEnderecoTest {
        
    public DAOEnderecoTest() {
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
        Endereco endereco = new Endereco("08667320", "SP","Sei la",100,"Rua Leonardo Fabricio Lopes");
        
        DAOEndereco DAOend = new DAOEndereco();
        DAOend.salvar(endereco);
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
        Endereco endereco = new Endereco();
        
        DAOEndereco DAOend = new DAOEndereco();
        List<EntidadeDominio> EntidadesEndereco = DAOend.consultar();
        
        for(int i=0;i<EntidadesEndereco.size();i++){
            endereco = (Endereco)EntidadesEndereco.get(i);
            System.out.println(endereco.getCep()+"\n"+endereco.getCidade()+"\n"+endereco.getEstado()+"\n"
                +endereco.getComplemento()+"\n"+endereco.getLogradouro());
            System.out.println("---------------------------------------------------------------------------");
        }
    }
//    
//    public void testExcluir() throws ParseException {
//        Endereco endereco = new Endereco("08440111", "MG","Sei la",100,"Rua Spinner Splaining");
//        
//        DAOEndereco DAOend = new DAOEndereco();
//        DAOend.excluir(endereco);
//    }
}
