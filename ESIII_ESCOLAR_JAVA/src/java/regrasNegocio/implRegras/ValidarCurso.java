/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regrasNegocio.implRegras;

import Dominio.Curso;
import Dominio.EntidadeDominio;
import Dominio.Pessoa;
import regrasNegocio.IStrategy;

/**
 *
 * @author 55119
 */
public class ValidarCurso implements IStrategy{
    @Override
    public String processar(EntidadeDominio entidade) {
        if(entidade instanceof Curso){
            StringBuilder sb = new StringBuilder();
            Curso curso = (Curso)entidade;
            
            if(curso.getNome()==null){
                sb.append("Nome está faltando!");
            }
            if(curso.getDescricao()==null){
                sb.append("Descricao está faltando!");
            }
            if(curso.getDuracao()==0){
                sb.append("Duração está faltando!");
            }
            if(curso.getMensalidade()==0){
                sb.append("Mensalidade faltando!");
            }
            
            if(sb.length()>0){
                return sb.toString();
            }
        }else{
            return "Entidade recebida Inválida, esperava Curso";
        }
        return null;
    }  
}
