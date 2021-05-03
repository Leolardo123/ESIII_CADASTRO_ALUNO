/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.ControleWeb;

import Dominio.EntidadeDominio;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eu
 */
public interface IViewHelper {
    
    public EntidadeDominio getEntidade(HttpServletRequest request);

    public void setView(Object resultado, 
                    HttpServletRequest request, HttpServletResponse response)throws ServletException;
    
}
