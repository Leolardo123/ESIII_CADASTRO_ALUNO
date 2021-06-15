/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regrasNegocio.implRegras;

import Dao.DAOCurso;
import Dominio.Curso;
import Dominio.EntidadeDominio;
import java.util.List;
import regrasNegocio.IStrategy;

/**
 *
 * @author 55119
 */
public class ValidarCurso implements IStrategy{
    public final String niveis[] = {"Tecnologo","Bacharelado","Doutorado",
                                    "Licenciatura","Graduação","Pós-Graduação","Mestrado"} ;
    @Override
    public String processar(EntidadeDominio entidade) {
        if(entidade instanceof Curso){
            StringBuilder sb = new StringBuilder();
            Curso curso = (Curso)entidade;
            
            DAOCurso DAOcur = new DAOCurso();

            List<EntidadeDominio> listCurso = DAOcur.consultar(curso);

            if (listCurso != null && listCurso.size() > 0) {
                Curso tempCurso = (Curso)listCurso.get(0);

                if (curso.getId() != 0) {
                    if (tempCurso != null && tempCurso.getId() != 0) {
                        curso.setId(tempCurso.getId());
                    } else {
                        sb.append("Curso " + curso.getId() + " não foi encontrado!");
                    }
                } else {
                    if (tempCurso != null && tempCurso.getNome().equals(curso.getNome())) {
                       sb.append("Curso com mesmo nome ("+curso.getNome()+") já existe!");
                    }
                }
            }
            
            boolean nivelValido = false;
            for(int i=0;i<niveis.length;i++){
                if(curso.getNivel().equals(niveis[i])){
                    nivelValido=true;
                }
            }
            if(nivelValido==false){
                sb.append("Nível do curso não é valido!");
            }
            if(curso.getNome()==null){
                sb.append("Nome está faltando!");
            }
            if(curso.getDescricao()==null){
                sb.append("Descricao está faltando!");
            }
            if(curso.getDuracao()<=0){
                sb.append("Duração está faltando ou é invalida!");
            }
            if(curso.getMensalidade()<=0){
                sb.append("Mensalidade faltando ou é invalida!");
            }
            
            if(sb.length()>0){
                return sb.toString();
            }
        }else{
            return "Entidade recebida Inválida, esperava Curso!";
        }
        return null;
    }  
}
