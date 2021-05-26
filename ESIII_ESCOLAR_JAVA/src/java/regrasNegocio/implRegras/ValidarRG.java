/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regrasNegocio.implRegras;

import Dominio.EntidadeDominio;
import Dominio.Pessoa;
import regrasNegocio.IStrategy;

/**
 *
 * @author 55119
 */
public class ValidarRG implements IStrategy {
    
        @Override
	public String processar(EntidadeDominio entidade) {
            Pessoa pessoa = (Pessoa)entidade;
            if(pessoa.getRg().length()==9){
                return null;
            }else{
                return "RG tem tamanho inválido!";
            }
        }  
}
