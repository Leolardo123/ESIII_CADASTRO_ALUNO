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
public class ConsultarCommand extends AbstractCommand {

    @Override
    public List<EntidadeDominio> execute(EntidadeDominio entidade) {
        return fachada.consultar(entidade);
    }
}
