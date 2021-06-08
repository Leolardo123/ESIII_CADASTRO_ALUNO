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
public class ItemGrade extends EntidadeDominio{
    private int dia_semana;
    private int turno;
    private int periodo;
    private Materia materia;
    private Professor professor;
    
    public ItemGrade() {}

    public ItemGrade(int dia_semana, int turno, int periodo, Materia materia, Professor professor) {
        this.dia_semana = dia_semana;
        this.turno = turno;
        this.periodo = periodo;
        this.materia = materia;
        this.professor = professor;
    }

    public int getDia_semana() {
        return dia_semana;
    }

    public void setDia_semana(int dia_semana) {
        this.dia_semana = dia_semana;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    
    public String getChave() {
        return turno+"-"+periodo+"-"+dia_semana;
    }
}
