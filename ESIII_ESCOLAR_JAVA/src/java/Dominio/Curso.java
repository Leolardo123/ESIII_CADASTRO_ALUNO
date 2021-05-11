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
public class Curso extends EntidadeDominio{
    private String nome;
    private String descricao;
    private String nivel;
    private int duracao;
    private double mensalidade;
    
    public Curso(){}
    
    public Curso(String nome,String descricao,String nivel,int duracao, double mensalidade){
        this.nome = nome;
        this.descricao = descricao;
        this.nivel = nivel;
        this.duracao = duracao;
        this.mensalidade = mensalidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public double getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(double mensalidade) {
        this.mensalidade = mensalidade;
    }
    
}
