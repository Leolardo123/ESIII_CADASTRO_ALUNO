/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regrasNegocio.implRegras;

import Dao.DAOPessoa;
import Dominio.EntidadeDominio;
import Dominio.Pessoa;
import java.util.List;
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
            
            DAOPessoa DAOpes = new DAOPessoa();
            List<EntidadeDominio> temppessoa = DAOpes.consultar(pessoa);
            
            if(temppessoa!=null&&temppessoa.size()>0){
                pessoa = (Pessoa)temppessoa.get(0);
                sb.append("Pessoa já existe!");
            }
                    
            if(pessoa.getPnome()==null){
                sb.append("Primeiro nome é obrigatório!");
            }
            if(pessoa.getPnome().length()>34){
                sb.append("Primeiro nome ultrapassa o limite de caracteres!");
            }
            if(pessoa.getUnome()==null){
                sb.append("Ultímo nome é obrigatório");
            }
            if(pessoa.getUnome().length()>254){
                sb.append("Ultímo nome ultrapassa o limite de caracteres!");
            }
            
            if(sb.length()>0)return sb.toString();
            //resto das validações
         }else{
            return "Entidade recebida Inválida, esperava Pessoa";
         }
         return null;
     }
}
