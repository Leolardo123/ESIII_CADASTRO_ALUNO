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
import Dominio.Materia;
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
        List<EntidadeDominio> EntidadesProfessor = daoPro.consultar();
        if(EntidadesProfessor!=null){
            professor  = (Professor)EntidadesProfessor.get(0);
        }
        
        DAOMateria dao = new DAOMateria();
        Materia materia = new Materia();
        
        List<EntidadeDominio> entidadesMateria = dao.consultar(null);
        
        if(entidadesMateria!=null){
            materia = (Materia)entidadesMateria.get(0);
        }
        
        
        if(materia!=null&&curso!=null&&professor!=null){
            GradeCurso grade = new GradeCurso( true, 1, 0, 1, curso, materia ,professor);

            DAOGrade daoGrade = new DAOGrade();
            daoGrade.salvar(grade);
        }
    }
    
    @Test
    public void testConsultar() {
        DAOGrade dao = new DAOGrade();
        
        List<EntidadeDominio> entidadeGrade = dao.consultar(null);
        
        
        for(EntidadeDominio entidade: entidadeGrade){
            GradeCurso grade = (GradeCurso)entidade;
            
            System.out.println("----------------------------------------");
            System.out.println(grade.isObrigatorio()+"\n"+grade.getDia_semana()+"\n"+grade.getTurno()+"\n"
                    +grade.getPeriodo()+"\n"+grade.getCurso()+"\n"+grade.getMateria()+"\n"+grade.getProfessor().getId());
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
            System.out.println(grade.isObrigatorio()+"\n"+grade.getDia_semana()+"\n"+grade.getTurno()+"\n"
                    +grade.getPeriodo()+"\n"+grade.getCurso()+"\n"+grade.getMateria()+"\n"+grade.getProfessor().getId());
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
