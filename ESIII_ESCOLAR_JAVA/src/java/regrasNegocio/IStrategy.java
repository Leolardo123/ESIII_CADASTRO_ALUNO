/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regrasNegocio;

import Dominio.EntidadeDominio;
import java.util.List;

/**
 *
 * @author 55119
 */
public interface IStrategy {
    public String processar(EntidadeDominio entidade);
}
