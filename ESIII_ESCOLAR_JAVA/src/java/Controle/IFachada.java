/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dominio.EntidadeDominio;
import java.util.List;

/**
 *
 * @author Eu
 */
public interface IFachada {
    
    public String cadastrar(EntidadeDominio entidade);
    public String alterar(EntidadeDominio entidade);
    public String excluir(EntidadeDominio entidade);
    public List<EntidadeDominio> consultar(EntidadeDominio entidade);
    
}
