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
public class Pessoa extends EntidadeDominio {

    String matricula;
    String cpf;
    String rg;
    String pnome;
    String unome;
    String email;
    Date dtnascimento;

    Endereco endereco;
    
    public Pessoa(){}

    public Pessoa(String matricula, String rg, String cpf, String pnome, 
            String unome, String email, Date dtnascimento, Endereco endereco) {
        this.matricula = matricula;
        this.cpf = cpf;
        this.rg = rg;
        this.pnome = pnome;
        this.unome = unome;
        this.email = email;
        this.dtnascimento = dtnascimento;
        
        this.endereco = endereco;
    }

    public Pessoa(Pessoa pessoa) {
        this.matricula = pessoa.matricula;
        this.cpf = pessoa.cpf;
        this.rg = pessoa.rg;
        this.pnome = pessoa.pnome;
        this.unome = pessoa.unome;
        this.email = pessoa.email;
        this.dtnascimento = pessoa.dtnascimento;
        
        this.endereco = pessoa.endereco;
    }
    
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getPnome() {
        return pnome;
    }

    public void setPnome(String pnome) {
        this.pnome = pnome;
    }

    public String getUnome() {
        return unome;
    }

    public void setUnome(String unome) {
        this.unome = unome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDtnasciemento() {
        return dtnascimento;
    }

    public void setDtnasciemento(Date dtnasciemento) {
        this.dtnascimento = dtnasciemento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
