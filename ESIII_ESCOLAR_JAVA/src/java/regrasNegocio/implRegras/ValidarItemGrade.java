/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regrasNegocio.implRegras;

import Dao.DAOItemGrade;
import Dominio.EntidadeDominio;
import Dominio.GradeCurso;
import Dominio.ItemGrade;
import java.util.List;
import regrasNegocio.IStrategy;

/**
 *
 * @author 55119
 */
public class ValidarItemGrade  implements IStrategy{
    public final String[] dias_validos = {null,"Segunda","Terça","Quarta","Quinta","Sexta","Sabado"};
    public final String[] periodos_validos = {null,"Manhã","Tarde","Noite"};
    public final String[] turnos_validos = {null,"primeiro","segundo"};
    
    @Override
    public String processar(EntidadeDominio entidade) {
            if(entidade instanceof ItemGrade){
                StringBuilder sb = new StringBuilder();
                ItemGrade item = (ItemGrade)entidade;
                
                ValidarMateria valMat = new ValidarMateria();
                ValidarProfessor valPro = new ValidarProfessor();
                String err;
                
                if(item.getMateria()==null){
                    sb.append("Falta Materia no Item Grade!");
                }else{
                    err = valMat.processar(item.getMateria());
                    
                    if(err!=null){
                        sb.append(err);
                    }
                }
                
                if(item.getProfessor()==null||item.getProfessor().getId()==0){
                    sb.append("Falta Professor no Item Grade!");
                }else{
                    err = valPro.processar(item.getProfessor());
                    
                    if(err!=null){
                        sb.append(err);
                    }
                }
                
                DAOItemGrade DAOigd = new DAOItemGrade();
                List<EntidadeDominio> listTempItem  = DAOigd.consultar(item);
                
                if(listTempItem!=null&&listTempItem.size()>0){
                    ItemGrade tempItem = (ItemGrade)listTempItem.get(0);
                    if(tempItem.getId()>0){
                        item = tempItem;
                    }
                }
                
                if(item.getDia_semana()<1 && item.getDia_semana()>dias_validos.length-1){
                    sb.append("Dia da Semana do Item Grade é inválido!");
                }
                if(item.getPeriodo()<1 && item.getPeriodo()>periodos_validos.length-1){
                    sb.append("Período do Item Grade é inválido!");
                }
                if(item.getTurno()<1 && item.getTurno()>turnos_validos.length-1){
                    sb.append("Turno do Item Grade é inválido!");
                }
                
                if(sb.length()>0)return sb.toString();
            }else{
                return "Entidade recebida Inválida, esperava GradeCurso";
            }
            return null;
    }  
}
