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
public class Materia extends EntidadeDominio{
    private String nome;
    private String descricao;
    private int carga_horaria;
    private int dependencia;
    
    public Materia(String nome, String descricao, int carga_horaria){
        this.nome = nome;
        this.descricao = descricao;
        this.carga_horaria = carga_horaria;
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

    public int getCarga_horaria() {
        return carga_horaria;
    }

    public void setCarga_horaria(int carga_horaria) {
        this.carga_horaria = carga_horaria;
    }

    public int getDependencia() {
        return dependencia;
    }

    public void setDependencia(int dependencia) {
        this.dependencia = dependencia;
    }
    
    
    
    
}
