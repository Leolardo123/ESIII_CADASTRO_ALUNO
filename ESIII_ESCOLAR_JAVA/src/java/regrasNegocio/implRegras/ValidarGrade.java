/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regrasNegocio.implRegras;

import Dominio.EntidadeDominio;
import Dominio.GradeCurso;
import Dominio.Pessoa;
import regrasNegocio.IStrategy;

/**
 *
 * @author 55119
 */
public class ValidarGrade implements IStrategy{
    private final String[] dias_validos = {null,"Segunda","Terça","Quarta","Quinta","Sexta","Sabado"};
    private final String[] periodos_validos = {null,"Manhã","Tarde","Noite"};
    private final String[] turnos_validos = {null,"primeiro","segundo"};
    
    @Override
    public String processar(EntidadeDominio entidade) {
            if(entidade instanceof GradeCurso){
                StringBuilder sb = new StringBuilder();
                GradeCurso grade = (GradeCurso)entidade;
                
                if(grade.getCurso()==null){
                    sb.append("Falta Curso na Grade!");
                }
                if(grade.getMateria()==null){
                    sb.append("Falta Materia na Grade!");
                }
                if(grade.getProfessor()==null){
                    sb.append("Falta Professor na Grade!");
                }
                if(grade.getDia_semana()<1 && grade.getDia_semana()>dias_validos.length-1){
                    sb.append("Dia da Semana da Grade é inválido!");
                }
                if(grade.getPeriodo()<1 && grade.getPeriodo()>periodos_validos.length-1){
                    sb.append("Período da Grade é inválido!");
                }
                if(grade.getTurno()<1 && grade.getTurno()>turnos_validos.length-1){
                    sb.append("Turno da Grade é inválido!");
                }
                if(grade.getSemestre()>grade.getCurso().getDuracao()){
                    sb.append("Semestre da Grade ultrapassa limite do Curso!");
                }
                if(grade.getSemestre()>99||grade.getSemestre()<1){
                    sb.append("Semestre é inválido");
                }
                if(sb.length()>0)return sb.toString();
            }else{
                return "Entidade recebida Inválida, esperava GradeCurso";
            }
            return null;
    }  
}
