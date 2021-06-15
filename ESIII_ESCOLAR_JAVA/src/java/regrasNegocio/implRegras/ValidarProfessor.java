/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regrasNegocio.implRegras;

import Dao.DAOProfessor;
import Dominio.EntidadeDominio;
import Dominio.Pessoa;
import Dominio.Professor;
import regrasNegocio.IStrategy;

/**
 *
 * @author 55119
 */
public class ValidarProfessor implements IStrategy{
     final static float min_salario = 1000.0f;
     @Override
     public String processar(EntidadeDominio entidade){
         Professor professor;
         if(entidade==null){
                 return "Falha ao receber professor!";
         }
         if(entidade instanceof Professor){
                   professor = (Professor)entidade;
            Pessoa pessoa    = (Pessoa)professor;
            
            StringBuilder sb = new StringBuilder();
            
            ValidarPessoa valPes = new ValidarPessoa();
            String msgPes        = valPes.processar(professor);

            if (msgPes != null) {
                sb.append(msgPes);
            }
            
            if(professor.getId()!=0){
                DAOProfessor DAOpro     = new DAOProfessor();
                Professor tempProfessor = (Professor)DAOpro.consultar(professor).get(0);
                
                if(tempProfessor == null){
                    sb.append("Não existe professor com id "+professor.getId()+"!");
                }
            }
            
            if(professor.getSalario()<min_salario){
                sb.append("Sálario do professor não pode ser menor que R$"+min_salario+"!");
            }
            
            if(sb.length()>0)return sb.toString();
         }else{
             return "Entidade recebida Inválida, esperava Pessoa";
         }
         return null;
     }
}
