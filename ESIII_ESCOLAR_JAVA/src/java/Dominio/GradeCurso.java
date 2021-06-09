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
    private int semestre;
    private int curso_id;
    
    public GradeCurso(){}
    
    public GradeCurso(List<ItemGrade> itensGrade,int semestre){
        this.itensGrade = itensGrade;
        this.semestre = semestre;
    }

    public List<ItemGrade> getItens() {
        return itensGrade;
    }

    public void setItens(List<ItemGrade> itensGrade) {
        this.itensGrade = itensGrade;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getCurso_id() {
        return curso_id;
    }

    public void setCurso_id(int curso_id) {
        this.curso_id = curso_id;
    }
    
    public String getChave() {
        return semestre+"-"+curso_id;
    }
    
    @Override
    public boolean equals(Object grade) {
        if (!(grade instanceof GradeCurso)) {
            return false;
        }
        GradeCurso outraGrade = (GradeCurso)grade;
        return outraGrade.getChave().equals(getChave());
    }
    
}