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
public class Professor extends Pessoa {
    private float salario;

    public Professor(float salario, String rg, String cpf, 
        String pnome, String unome, String email, Date dtNascimento, Endereco endereco) {
        super(rg, cpf, pnome, unome, email, dtNascimento, endereco);
        this.salario = salario;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }   
}
