/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regrasNegocio.implRegras;

import Dominio.EntidadeDominio;
import Dominio.GradeCurso;
import Dominio.Materia;
import regrasNegocio.IStrategy;

/**
 *
 * @author 55119
 */
public class ValidarMateria implements IStrategy{
    @Override
    public String processar(EntidadeDominio entidade) {
            if(entidade instanceof Materia){
                StringBuilder sb = new StringBuilder();
                Materia materia = (Materia)entidade;
                
                if(materia.getNome()==null){
                    sb.append("Falta Nome na Materia!");
                }
                if(materia.getDescricao()==null){
                    sb.append("Falta Descricao na Grade!");
                }
                
                ValidarDependencias valDep = new ValidarDependencias();
                for(Materia dependencia:materia.getDependencias()){
                    String msgDep = valDep.processar(dependencia);
                    if(msgDep!=null)return msgDep;
                }
                
                if(sb.length()>0)return sb.toString();
            }else{
                return "Entidade recebida Inválida, esperava Matéria";
            }
            return null;
    }  
}
