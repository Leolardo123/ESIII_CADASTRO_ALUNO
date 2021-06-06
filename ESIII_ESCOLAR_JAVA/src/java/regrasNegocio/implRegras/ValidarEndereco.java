/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regrasNegocio.implRegras;

import Dao.DAOEndereco;
import Dominio.Endereco;
import Dominio.EntidadeDominio;
import regrasNegocio.IStrategy;

/**
 *
 * @author 55119
 */
public class ValidarEndereco implements IStrategy{
    @Override
    public String processar(EntidadeDominio entidade) {
            if(entidade instanceof Endereco){
                StringBuilder sb = new StringBuilder();
                Endereco endereco = (Endereco)entidade;
                
                DAOEndereco DAOend = new DAOEndereco();
                endereco = new Endereco((Endereco)DAOend.consultar(endereco).get(0));
                
                if(endereco.getCep()==null){
                    sb.append("Falta CEP do Endereco!");
                }
                if(endereco.getCidade()==null){
                    sb.append("Falta Cidade do Endereco!");
                }
                if(endereco.getEstado()==null){
                    sb.append("Falta Estado do Endereco!");
                }
                if(endereco.getLogradouro()==null){
                    sb.append("Falta Logradouro do Endereco!");
                }
                if(endereco.getNumero()<=0){
                    sb.append("Número do endereco é inválido!");
                }
                if(endereco.getNumero()>999999){
                    sb.append("Número do endereco é maior que o limite de 6 dígitos!");
                }
                if(sb.length()>0)return sb.toString();
            }else{
                return "Entidade recebida Inválida, esperava GradeCurso";
            }
            return null;
    }  
}
