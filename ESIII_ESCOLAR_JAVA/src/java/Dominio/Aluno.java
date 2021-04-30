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
public class Aluno extends Pessoa{
    private int semestre;
    
    public Aluno(Pessoa pessoa,int semestre){
        super(pessoa);
        this.semestre = semestre;
    }
}
