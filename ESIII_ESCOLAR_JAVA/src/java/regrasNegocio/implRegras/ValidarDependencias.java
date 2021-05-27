/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regrasNegocio.implRegras;

import Dao.DAODependentes;
import Dominio.EntidadeDominio;
import Dominio.Materia;
import regrasNegocio.IStrategy;

/**
 *
 * @author 55119
 */
public class ValidarDependencias implements IStrategy{
    @Override
    public String processar(EntidadeDominio entidade) {
        if(entidade instanceof Materia){
                StringBuilder sb = new StringBuilder();
                Materia dependencia = (Materia)entidade;
                
                if(dependencia.getId()==0){
                    return "Dependencia sem id, verifique se ela existe!";
                }
                
                DAODependentes dao = new DAODependentes();
                dao.consultar(dependencia);
                
                if(dependencia.getNome()==null){
                    sb.append("Falta Nome na Dependencia!");
                }
                if(dependencia.getDescricao()==null){
                    sb.append("Falta Descricao na Dependencia!");
                }
                if(sb.length()>0)return sb.toString();
            }else{
                return "Entidade recebida Inválida, esperava Matéria";
            }
            return null;
    }  
}
