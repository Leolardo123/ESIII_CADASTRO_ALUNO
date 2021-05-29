/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dominio.Curso;
import Dominio.Endereco;
import Dominio.EntidadeDominio;
import Dominio.GradeCurso;
import Dominio.ItemGrade;
import Dominio.Materia;
import Dominio.Professor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public void testSalvar() throws ParseException {
        //Carrega um curso qualquer
        Curso curso = new Curso();
                
        DAOCurso daoCur = new DAOCurso();
        List<EntidadeDominio> EntidadesCursos = daoCur.consultar(null);
        if(EntidadesCursos!=null){
            curso = (Curso)EntidadesCursos.get(0);
        }
        
       //Carrega um professor qualquer
       Professor professor = new Professor();
        
        DAOProfessor daoPro = new DAOProfessor();
        List<EntidadeDominio> EntidadesProfessor = daoPro.consultar(null);
        if(EntidadesProfessor!=null){
            professor  = (Professor)EntidadesProfessor.get(0);
            System.out.println(professor.getId());
        }
        
        DAOMateria dao = new DAOMateria();
        Materia materia = new Materia();
        
        List<EntidadeDominio> entidadesMateria = dao.consultar(null);
        
        if(entidadesMateria!=null){
            materia = (Materia)entidadesMateria.get(0);
            System.out.println(materia.getId());
        }
        
        if(materia!=null&&curso!=null&&professor!=null){
            ItemGrade item = new ItemGrade(true, 1, 1, 1, materia ,professor); 
            List<ItemGrade> itemList = new ArrayList<ItemGrade>();
            itemList.add(item);
            
            GradeCurso grade = new GradeCurso(itemList,curso,1);
            
            DAOGrade daoGrade = new DAOGrade();
            daoGrade.salvar(grade);
        }
    }
    
    @Test
    public void testConsultar() {
        GradeCurso grade = new GradeCurso();
        
        DAOGrade dao = new DAOGrade();
        
        List<EntidadeDominio> entidadeGrade = dao.consultar(null);
        
        for(EntidadeDominio entidade: entidadeGrade){
            grade = (GradeCurso)entidade;
            System.out.println("----------------------------------------");
            System.out.println(grade.getCurso().getNome()+"\n"+grade.getCurso().getNivel()+"\n"+grade.getCurso().getDuracao()+"\n");
            System.out.println("ITENS:");
            for(ItemGrade item: grade.getItens()){
                 System.out.println(item.isObrigatorio()+"\n"+item.getDia_semana()+"\n"+item.getTurno()+"\n"
                    +item.getPeriodo()+"\n"+grade.getCurso()+"\n"+item.getMateria().getNome()+"\n"+item.getProfessor().getId());
                System.out.println("----------------------------------------");
            }
        }
    }
    
    @Test
    public void testConsultarId() {
        GradeCurso grade = new GradeCurso();
        grade.setId(1);
        
        DAOGrade dao = new DAOGrade();
        
        List<EntidadeDominio> entidadeGrade = dao.consultar(grade);
        
        for(EntidadeDominio entidade: entidadeGrade){
            grade = (GradeCurso)entidade;
            System.out.println("----------------------------------------");
            System.out.println(grade.getCurso().getNome()+"\n"+grade.getCurso().getNivel()+"\n"+grade.getCurso().getDuracao()+"\n");
            System.out.println("ITENS:");
            for(ItemGrade item: grade.getItens()){
                 System.out.println(item.isObrigatorio()+"\n"+item.getDia_semana()+"\n"+item.getTurno()+"\n"
                    +item.getPeriodo()+"\n"+grade.getCurso()+"\n"+item.getMateria().getNome()+"\n"+item.getProfessor().getId());
                System.out.println("----------------------------------------");
            }
        }
    }
    
    
    @Test
    public void testExcluir() {
        GradeCurso grade = new GradeCurso();
        grade.setId(1);
        
        DAOGrade dao = new DAOGrade();
        dao.excluir(grade);
    }
    
}
