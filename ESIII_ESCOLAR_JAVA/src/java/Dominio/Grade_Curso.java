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
public class Grade_Curso  extends EntidadeDominio{
    private boolean obrigatorio;
    private int dia_semana;
    private int turno;
    private int periodo;
    
    public Grade_Curso(){}
    
    public Grade_Curso(boolean obrigatorio,int dia_semana,int turno,int periodo){
        this.obrigatorio = obrigatorio;
        this.dia_semana = dia_semana;
        this.turno = turno;
        this.periodo = periodo;
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
    
}
