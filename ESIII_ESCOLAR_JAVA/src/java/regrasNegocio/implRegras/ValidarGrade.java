/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regrasNegocio.implRegras;

import Dao.DAOGrade;
import Dao.DAOItemGrade;
import Dominio.EntidadeDominio;
import Dominio.GradeCurso;
import Dominio.ItemGrade;
import Dominio.Pessoa;
import java.util.HashMap;
import java.util.List;
import regrasNegocio.IStrategy;

/**
 *
 * @author 55119
 */
public class ValidarGrade implements IStrategy{
    @Override
    public String processar(EntidadeDominio entidade) {
            if(entidade instanceof GradeCurso){
                HashMap<String,ItemGrade> PrimaryKey = new HashMap<String,ItemGrade>();//representa a PK item grade: PK_GRI(mat_id,cur_id)
                
                StringBuilder sb = new StringBuilder();
                GradeCurso grade = (GradeCurso)entidade;
                
                DAOGrade DAOgra = new DAOGrade();
                List<EntidadeDominio> entidadeGrade = DAOgra.consultar(grade);
                
                if(entidadeGrade!=null&&entidadeGrade.size()>0){
                    grade = (GradeCurso)entidadeGrade.get(0);
                    sb.append("GradeCurso já existe!");
                }
                
                if(grade.getCurso()==null){
                    sb.append("Falta Curso na Grade!");
                }
                
                int count = 0;
                
                ValidarItemGrade valItem = new ValidarItemGrade();
                
                DAOItemGrade DAOIgd = new DAOItemGrade();

                for(ItemGrade itemGrade:grade.getItens()){
                    String MsgItem = valItem.processar(itemGrade);
                    if(MsgItem!=null){
                        sb.append("ItemGrade");
                        sb.append(count);
                        sb.append("-");
                        sb.append(MsgItem);
                        sb.append("!");
                    }
                    
                    String pk = itemGrade.getMateria().getId()+"-"+grade.getCurso().getId();//ex: 109-2 (materia-curso)
                    if(PrimaryKey.get(pk)==null){//verifica se item ja esta no hashmap, assim sabendo se está repetido
                        PrimaryKey.put(pk, itemGrade);//coloca item no hashmap
                    }else{
                        sb.append("ItemGrade ");
                        sb.append(count);
                        sb.append(" Materia está repetida e não pode,item:(id_materia-id_curso)->(");
                        sb.append(pk);
                        sb.append(")!");
                        //Erro de item repetido,ex: ItemGrade 2 Materia está repetida e não pode,item:(id_materia-id_curso)->(109-2)!
                    }
                }
                
                if (grade.getSemestre() > grade.getCurso().getDuracao()) {
                    sb.append("-Semestre da grade ultrapassa limite do Curso!");
                }
                if (grade.getSemestre() > 99 || grade.getSemestre() < 1) {
                    sb.append("-Semestre da grade é inválido!");
                }
                
                if(sb.length()>0)return sb.toString();
            }else{
                return "Entidade recebida Inválida, esperava GradeCurso";
            }
            return null;
    }  
}
