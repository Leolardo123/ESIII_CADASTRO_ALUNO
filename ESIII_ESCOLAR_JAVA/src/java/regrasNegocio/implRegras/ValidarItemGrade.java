/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regrasNegocio.implRegras;

import Dominio.EntidadeDominio;
import Dominio.GradeCurso;
import Dominio.ItemGrade;
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
                ItemGrade grade = (ItemGrade)entidade;
                
                if(grade.getMateria()==null){
                    sb.append("Falta Materia no Item Grade!");
                }
                if(grade.getProfessor()==null){
                    sb.append("Falta Professor no Item Grade!");
                }
                if(grade.getDia_semana()<1 && grade.getDia_semana()>dias_validos.length-1){
                    sb.append("Dia da Semana do Item Grade é inválido!");
                }
                if(grade.getPeriodo()<1 && grade.getPeriodo()>periodos_validos.length-1){
                    sb.append("Período do Item Grade é inválido!");
                }
                if(grade.getTurno()<1 && grade.getTurno()>turnos_validos.length-1){
                    sb.append("Turno do Item Grade é inválido!");
                }
                
                if(sb.length()>0)return sb.toString();
            }else{
                return "Entidade recebida Inválida, esperava GradeCurso";
            }
            return null;
    }  
}
