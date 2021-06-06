/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dominio.EntidadeDominio;

/**
 *
 * @author Eu
 */
public class ExcluirCommand extends AbstractCommand {

    @Override
    public String execute(EntidadeDominio entidade) {
        return fachada.excluir(entidade);
    }

}
