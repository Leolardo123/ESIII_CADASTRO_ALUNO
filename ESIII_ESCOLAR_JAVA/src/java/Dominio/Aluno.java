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
    
    public Aluno(String matricula, String rg, String cpf, String pnome, 
            String unome, String email, Date dtNascimento, Endereco endereco,
            int semestre){
        super(matricula, rg, cpf, pnome, unome, email, dtNascimento, endereco);
        this.semestre = semestre;
    }
}
