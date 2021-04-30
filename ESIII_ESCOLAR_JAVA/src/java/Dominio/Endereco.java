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
public class Endereco extends EntidadeDominio{
    private String cep;
    private String estado;  
    private String cidade;
    private int numero;
    private String logradouro;
    private String complemento;
    
    public Endereco(){}
    
    public Endereco(String cep,String estado,String cidade,int numero,String logradouro,String complemento){
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
        this.numero = numero;
        this.logradouro = logradouro;
        this.complemento = complemento;
    }
    
    public Endereco(String cep,String estado,String cidade,int numero,String logradouro){
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
        this.numero = numero;
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    
}
