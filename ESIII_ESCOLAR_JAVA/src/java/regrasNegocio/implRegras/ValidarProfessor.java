/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regrasNegocio.implRegras;

import Dominio.EntidadeDominio;
import Dominio.Pessoa;
import Dominio.Professor;
import regrasNegocio.IStrategy;

/**
 *
 * @author 55119
 */
public class ValidarProfessor implements IStrategy{
     @Override
     public String processar(EntidadeDominio entidade){
         Pessoa pessoa;
         Professor professor;
         if(entidade instanceof Professor){
            professor = (Professor)entidade;
            pessoa = (Pessoa)professor;
            
            StringBuilder sb = new StringBuilder();
             
            ValidarPessoa valPes = new ValidarPessoa();
            String msgPes = valPes.processar(pessoa);
            if(msgPes!=null){
                sb.append(msgPes);
            }
            
            if(professor.getSalario()<=0){
                sb.append("Sálario do professor é inválido!");
            }
            
            if(sb.length()>0)return sb.toString();
         }else{
             return "Entidade recebida Inválida, esperava Pessoa";
         }
         return null;
     }
}
