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
    private int curso_id;
    
    public Aluno(String rg, String cpf, String pnome, 
            String unome, String email, Date dtNascimento, Endereco endereco,
            int semestre, int curso_id){
        
        super(rg, cpf, pnome, unome, email, dtNascimento, endereco);
        this.semestre = semestre;
        this.curso_id = curso_id;
    }
    
    public Aluno(Pessoa pessoa,int semestre, int curso_id){
        
        super(pessoa.getRg(), pessoa.getCpf(), pessoa.getPnome(), pessoa.getUnome(),
                pessoa.getEmail(), pessoa.getDtNascimento(), pessoa.getEndereco());
        this.semestre = semestre;
        this.curso_id = curso_id;
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
    
}
