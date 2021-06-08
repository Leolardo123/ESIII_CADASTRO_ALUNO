/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regrasNegocio.implRegras;

import Dao.DAOEndereco;
import Dao.DAOPessoa;
import Dominio.Endereco;
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
            
            if(pessoa.getEndereco()!=null){
                DAOEndereco DAOEnd = new DAOEndereco();
                if(pessoa.getEndereco().getId()!=0){    
                    Endereco tempEnd = (Endereco)DAOEnd.consultar(pessoa.getEndereco()).get(0);
                    if(tempEnd!=null&&tempEnd.getId()!=0){
                        pessoa.setEndereco(tempEnd);
                    }else{
                        sb.append("Endereco da pessoa não foi encontrado!");
                    }
                }   
                   
                ValidarEndereco valEnd = new ValidarEndereco();
                String msgEnd = valEnd.processar(pessoa.getEndereco());
                
                if(msgEnd!=null){
                    sb.append(msgEnd);
                }
            }else{
                sb.append("Endereco da pessoa está vazio!");
            }
            
            DAOPessoa DAOpes = new DAOPessoa();
            List<EntidadeDominio> temppessoa = DAOpes.consultar(pessoa);
            
            if(temppessoa!=null&&temppessoa.size()>0){
                pessoa = (Pessoa)temppessoa.get(0);
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
