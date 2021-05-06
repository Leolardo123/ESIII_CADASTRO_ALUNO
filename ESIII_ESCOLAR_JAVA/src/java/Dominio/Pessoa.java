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

    private String matricula;
    private String cpf;
    private String rg;
    private String pnome;
    private String unome;
    private String email;
    private Date dtNascimento;

    Endereco endereco;
    
    public Pessoa(){}

    public Pessoa(String rg, String cpf, String pnome, 
            String unome, String email, Date dtNascimento, Endereco endereco) {
        this.cpf = cpf;
        this.rg = rg;
        this.pnome = pnome;
        this.unome = unome;
        this.email = email;
        this.dtNascimento = dtNascimento;
        
        this.endereco = endereco;
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
        return dtNascimento;
    }

    public void setDtnasciemento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
