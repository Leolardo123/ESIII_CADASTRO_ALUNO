/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regrasNegocio.implRegras;

import Dao.DAOCurso;
import Dao.DAOGrade;
import Dominio.Curso;
import Dominio.EntidadeDominio;
import Dominio.GradeCurso;
import Dominio.ItemGrade;
import java.util.HashMap;
import java.util.List;
import regrasNegocio.IStrategy;

/**
 *
 * @author 55119
 */
public class ValidarGrade implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {
        if (entidade instanceof GradeCurso) {
            GradeCurso grade =(GradeCurso)entidade;
            StringBuilder sb = new StringBuilder();

            Curso curso = new Curso();
            curso.setId(grade.getCurso_id());
            
            if (grade.getCurso_id() <= 0) {
                return "Curso ainda precisa ser selecionado!";
            }
            
            ValidarCurso valCur = new ValidarCurso();
            String MsgCur = valCur.processar(curso);
            
            if(MsgCur!=null){
                sb.append(MsgCur);
            }
            
            DAOCurso DAOcur = new DAOCurso();
            Curso tempcurso = (Curso)DAOcur.consultar(curso).get(0);
            
            if(tempcurso!=null&&tempcurso.getId()!=0){
                curso = tempcurso;
            }else{
                return "Curso não encontrado";
            }

            if (grade.getSemestre() > curso.getDuracao()&&curso.getId()!=0) {
                sb.append("Semestre da grade ultrapassa limite do Curso!");
            }

            if (grade.getSemestre() > 99 || grade.getSemestre() < 1) {
                sb.append("Semestre da grade  é inválido!");
            }

            DAOGrade DAOgra = new DAOGrade();
            List<EntidadeDominio> entidadeGrade = DAOgra.consultar(grade);

            if (entidadeGrade != null && entidadeGrade.size() > 0) {
                grade = (GradeCurso) entidadeGrade.get(0);
            }

            int count = 0;

            ValidarItemGrade valItem = new ValidarItemGrade();

            if(grade.getItens()!=null){
                for (ItemGrade itemGrade : grade.getItens()) {
                    String MsgItem = valItem.processar(itemGrade);
                    if (MsgItem != null) {
                        sb.append("ItemGrade");
                        sb.append(count);
                        sb.append("-");
                        sb.append(MsgItem);
                        sb.append("!");
                    }
                }
            }

            if (sb.length() > 0) {
                return sb.toString();
            }
        } else {
            return "Entidade recebida Inválida, esperava GradeCurso";
        }
        return null;
    }
}
