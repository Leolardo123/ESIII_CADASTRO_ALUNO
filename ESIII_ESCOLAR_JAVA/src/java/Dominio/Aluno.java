/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.Date;

/**
 *
 * @author 55119
 */
public class Aluno extends Pessoa{
    private int semestre;
    private Curso curso;
    
    public Aluno() {}
    
    public Aluno (Pessoa pessoa){
        super(pessoa);
    }
    
    public Aluno(Pessoa pessoa,int semestre, Curso curso) {
        super(pessoa);
        this.semestre = semestre;
        this.curso = curso;
    }
    
    public Aluno(String rg, String cpf, String pnome, 
            String unome, String email, Date dtNascimento, Endereco endereco,
            int semestre, Curso curso){
        
        super(rg, cpf, pnome, unome, email, dtNascimento, endereco);
        this.semestre = semestre;
        this.curso = curso;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
