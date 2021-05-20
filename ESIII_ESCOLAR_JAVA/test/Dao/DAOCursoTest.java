package Dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Dominio.Curso;
import Dominio.EntidadeDominio;
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
    public void testConsultar(){
        int id = 1;
        
        DAOCurso dao = new DAOCurso();
        List<EntidadeDominio> EntidadeCursos = dao.consultar(id);
        if(EntidadeCursos!=null){
            for(EntidadeDominio entidade: EntidadeCursos){
                Curso curso = (Curso)entidade;

                System.out.println(curso.getId()+"\n"+curso.getNome()+"\n"+curso.getDescricao()+"\n"+curso.getDtcadastro()+"\n"
                    +curso.getMensalidade()+"\n"+curso.getDuracao()+"\n"+curso.getNivel());

                System.out.println("\n------------------------------------------------------------------------------");

            }
        }
    }
    
    @Test
    public void testConsultarId(){;
        int id = 1;
        
        DAOCurso dao = new DAOCurso();
        List<EntidadeDominio> EntidadeCursos = dao.consultar();
        
        if(EntidadeCursos!=null){
            for(EntidadeDominio entidade: EntidadeCursos){
                Curso curso = (Curso)entidade;

                System.out.println(curso.getId()+"\n"+curso.getNome()+"\n"+curso.getDescricao()+"\n"+curso.getDtcadastro()+"\n"
                    +curso.getMensalidade()+"\n"+curso.getDuracao()+"\n"+curso.getNivel());

                System.out.println("\n------------------------------------------------------------------------------");

            }
        }
    }

    
    @Test
    public void testAlterar(){;
        Curso curso;
        curso = new Curso("ADS", "Curso de TI","Tecnologo",48, 240.00);
        DAOCurso dao = new DAOCurso();
        
        dao.salvar(curso);
        
        curso.setId(999);
        
        curso.setDescricao("Curso de Humanas");
        
        dao.alterar(curso);
    }
    
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
        curso.setId(999);
        
        DAOCurso dao = new DAOCurso();
        dao.excluir(curso);
    }
    
}
