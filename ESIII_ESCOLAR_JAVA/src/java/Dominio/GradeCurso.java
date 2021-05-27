/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 * @author 55119
 */
public class GradeCurso  extends EntidadeDominio{
    private boolean obrigatorio;
    private int dia_semana;
    private int turno;
    private int periodo;
    private int semestre;
    private Curso curso;
    private Materia materia;
    private Professor professor;
    
    public GradeCurso(){}
    
    public GradeCurso(boolean obrigatorio,int dia_semana,int turno,int periodo, 
                      Curso curso, Materia materia, Professor professor){
        this.obrigatorio = obrigatorio;
        this.dia_semana = dia_semana;
        this.turno = turno;
        this.periodo = periodo;
        this.curso = curso;
        this.materia = materia;
        this.professor = professor;
    }

    public boolean isObrigatorio() {
        return obrigatorio;
    }

    public void setObrigatorio(boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
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
    
    public int getSemestre() {
        return semestre;
    }

    public void setCurso(int semestre) {
        this.semestre = semestre;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
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
}
