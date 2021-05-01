/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dominio.EntidadeDominio;

/**
 *
 * @author Eu
 */
public abstract class AbstractIDAO implements IDAO{
    protected Object conexao;
    
    protected void conectar(){}
    public void excluir() {}

}
