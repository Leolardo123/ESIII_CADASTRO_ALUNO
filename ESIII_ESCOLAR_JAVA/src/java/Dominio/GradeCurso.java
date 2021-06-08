/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 55119
 */
public class GradeCurso  extends EntidadeDominio{
    private List<ItemGrade> itensGrade;
    private Curso curso;
    private int semestre;
    
    public GradeCurso(){}
    
    public GradeCurso(List<ItemGrade> itensGrade,Curso curso,int semestre){
        this.itensGrade = itensGrade;
        this.curso = curso;
        this.semestre = semestre;
    }

    public List<ItemGrade> getItens() {
        return itensGrade;
    }

    public void setItens(List<ItemGrade> itensGrade) {
        this.itensGrade = itensGrade;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
    
}