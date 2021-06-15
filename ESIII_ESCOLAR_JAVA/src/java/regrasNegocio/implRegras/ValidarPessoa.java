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
import java.util.ArrayList;
import java.util.List;
import regrasNegocio.IStrategy;

/**
 *
 * @author 55119
 */
public class ValidarPessoa implements IStrategy{
    int max_pnome_char = 35;
    int max_unome_char = 255;
     @Override
     public String processar(EntidadeDominio entidade){
         Pessoa pessoa;
         if(entidade==null){
             return "Falha ao receber pessoa!";
         }
         if(entidade instanceof Pessoa){
            pessoa = (Pessoa)entidade;
            
            StringBuilder sb = new StringBuilder();
            
            String msgCPF = null;
            
            if((pessoa.getCpf()!=null&&pessoa.getRg()!=null)||pessoa.getId()>0){//if consulta
                DAOPessoa DAOpes  = new DAOPessoa();
                    
                List<EntidadeDominio> entPessoas = DAOpes.consultar(pessoa);//busca uma pessoa com rg ou cpf igual
                Pessoa tempPessoa = null;
                
                if(entPessoas!=null&&entPessoas.size()>0){
                    tempPessoa = (Pessoa)entPessoas.get(0);
                }

                if(tempPessoa != null && pessoa.getCpf()!=null && pessoa.getRg()!=null){
                    
                    if(tempPessoa.getId()==pessoa.getId()){//se for a mesma pessoa...
                        if(!(tempPessoa.getCpf().equals(pessoa.getCpf()))){//verifica se cpf foi modificado
                            sb.append("CPF nunca pode ser modificado!");
                        }
                        if(!(tempPessoa.getRg().equals(pessoa.getRg()))){//verifica se rg foi modificado
                            sb.append("RG nunca pode ser modificado!");
                        }
                    }else{                                               //senao for a mesma pessoa...
                        if(tempPessoa.getCpf().equals(pessoa.getCpf())){//verifica se cpf ja foi cadastrado
                            sb.append("Já existe uma pessoa com esse CPF!");
                        }
                        if(tempPessoa.getRg().equals(pessoa.getRg())){//verifica se rg ja foi cadastrado
                            sb.append("Já existe uma pessoa com esse RG!");
                        }
                    }
                }
                
                if(pessoa.getCpf()!=null){
                    ValidarCPF valCPF = new ValidarCPF();
                    msgCPF = valCPF.processar(pessoa);

                    if(msgCPF!=null){
                        sb.append(msgCPF);
                    }
                }else{
                    sb.append("CPF é obrigatório!");
                }
            
                String msgRG = null;
                if(pessoa.getCpf()!=null){
                    ValidarRG valRG = new ValidarRG();
                    msgRG= valRG.processar(pessoa);

                    if(msgRG!=null){
                        sb.append(msgRG);
                    }
                }else{
                    sb.append("RG é obrigatório!");
                }
            }else{
                sb.append("Não foi possivel validar a pessoa!");//sem rg e cpf e id validos não há como verificar se estes já existem
            }//Fim if consulta
                    
            if(pessoa.getPnome()==null){
                sb.append("Primeiro nome é obrigatório!");
            }
            if(pessoa.getPnome().length()>max_pnome_char){
                sb.append("Primeiro nome ultrapassa o limite de "+max_pnome_char+" caracteres!");
            }
            if(pessoa.getUnome()==null){
                sb.append("Ultímo nome é obrigatório");
            }
            if(pessoa.getUnome().length()>max_pnome_char){
                sb.append("Ultímo nome ultrapassa o limite de de "+max_unome_char+" caracteres!");
            }
            
            if(sb.length()>0)return sb.toString();
         }else{
            return "Entidade recebida Inválida, esperava Pessoa!";
         }
         return null;
     }
}
