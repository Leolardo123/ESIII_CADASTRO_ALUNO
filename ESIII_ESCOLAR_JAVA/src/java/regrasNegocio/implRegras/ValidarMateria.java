/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regrasNegocio.implRegras;

import Dao.DAOMateria;
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
                
                

                DAOMateria DAOmat = new DAOMateria();
                Materia consmateria = new Materia((Materia) DAOmat.consultar(materia).get(0));

                if (consmateria != null) {
                    materia = consmateria;
                    sb.append("Materia já existe!");
                }

                if(materia.getNome()==null){
                    sb.append("Falta Nome na Materia!");
                }
                if(materia.getDescricao()==null){
                    sb.append("Falta Descricao na Materia!");
                }
                
                if(materia.getCarga_horaria()<=0){
                    sb.append("Carga horária deve ser maior que 0!");
                }
                
                if(materia.getCarga_horaria()>999999){
                    sb.append("Carga horária deve ser menor que 999999!");
                }
                
                if(materia.getDependencias()!=null){
                    ValidarDependencias valDep = new ValidarDependencias();
                    String msgDep = valDep.processar(materia);
                    
                    if (msgDep != null) {
                        sb.append(msgDep);
                    }
                }

                if (sb.length() > 0) {
                    return sb.toString();
                }
                
                if(sb.length()>0)return sb.toString();
            }else{
                return "Entidade recebida Inválida, esperava Matéria";
            }
            return null;
    }  
}
