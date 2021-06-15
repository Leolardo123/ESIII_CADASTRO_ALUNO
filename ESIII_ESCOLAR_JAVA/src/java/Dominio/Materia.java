/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 55119
 */
public class Materia extends EntidadeDominio{
    private String nome;
    private String descricao;
    private int carga_horaria;
    private List<Materia> dependencias;
    
    public Materia(){}
    
    public Materia(String nome, String descricao, int carga_horaria){
        this.nome = nome;
        this.descricao = descricao;
        this.carga_horaria = carga_horaria;
    }
    
    public Materia(String nome, String descricao, int carga_horaria, List<Materia> dependencia){
        this.nome = nome;
        this.descricao = descricao;
        this.carga_horaria = carga_horaria;
        this.dependencias = dependencia;
    }

    public Materia(Materia materia) {
        this.nome = materia.nome;
        this.descricao = materia.descricao;
        this.carga_horaria = materia.carga_horaria;
        this.dependencias = materia.dependencias;
        this.setId(materia.getId());
        this.setDtcadastro(materia.getDtcadastro());
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

    public List<Materia> getDependencias() {
        return dependencias;
    }

    public void setDependencias(List<Materia> dependencias) {
        this.dependencias = dependencias;
    }
    
     @Override 
     public boolean equals(Object entidade) {
        if (!(entidade instanceof Materia))
            return false;
        Materia mat = (Materia)entidade;
        return mat.getId()==this.getId() || 
                mat.getNome().equals(this.getNome());
    }
}
