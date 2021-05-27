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
public class ValidarPessoa implements IStrategy{
     @Override
     public String processar(EntidadeDominio entidade){
         Pessoa pessoa;
         if(entidade instanceof Pessoa){
            pessoa = (Pessoa)entidade;
            
            StringBuilder sb = new StringBuilder();
             
            ValidarCPF valCpf = new ValidarCPF();
            String msgCPF = valCpf.processar(pessoa);
            
            if(msgCPF!=null){
                sb.append(msgCPF);
            }
            
            ValidarRG valRg = new ValidarRG();
            String msgRG = valRg.processar(pessoa);
            

            if(msgRG!=null){
                sb.append(msgRG);
            }
            
            //resto das validações
         }else{
             return "Entidade recebida Inválida, esperava Pessoa";
         }
         return null;
     }
}
