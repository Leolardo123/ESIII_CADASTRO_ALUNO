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

    private String cpf;
    private String rg;
    private String pnome;
    private String unome;
    private String email;
    private Date dtNascimento;

    Endereco endereco;
    
    public Pessoa(){}
    
    public Pessoa(Pessoa pessoa){
        this.cpf = pessoa.getCpf();
        this.rg = pessoa.getRg();
        this.pnome = pessoa.getPnome();
        this.unome = pessoa.getUnome();
        this.email = pessoa.getEmail();
        this.dtNascimento = pessoa.getDtNascimento();
        
        this.endereco = pessoa.getEndereco();
    }

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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }  
}
