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
public class ValidarCPF implements IStrategy{
    
    	@Override
	public String processar(EntidadeDominio entidade) {
            if(entidade instanceof Pessoa){
                Pessoa pessoa = (Pessoa)entidade;
                if(pessoa.getCpf().length()==11){
                    return null;
                }else{
                    return "CPF tem tamanho inválido!";
                }
            }else{
                 return "Entidade recebida Inválida, esperava Pessoa(CPF)";
            }
        }  
}  
