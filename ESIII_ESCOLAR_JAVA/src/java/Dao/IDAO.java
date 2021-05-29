/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dominio.*;
import java.util.List;

/**
 *
 * @author Eu
 */
public interface IDAO {
    public List<String> getColunas(EntidadeDominio entidade);
    public void salvar(EntidadeDominio entidade);
    public void alterar(EntidadeDominio entidade);
    public void excluir(EntidadeDominio entidade);
    public List<EntidadeDominio> consultar(EntidadeDominio entidade);//corrigir tipo da lista (erro nos DAO's que a herdam)
}
