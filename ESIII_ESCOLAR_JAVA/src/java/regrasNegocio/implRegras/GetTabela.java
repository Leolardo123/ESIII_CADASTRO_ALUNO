/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regrasNegocio.implRegras;

import Dominio.Aluno;
import Dominio.Curso;
import Dominio.Endereco;
import Dominio.EntidadeDominio;
import Dominio.GradeCurso;
import Dominio.ItemGrade;
import Dominio.Materia;
import Dominio.Pessoa;
import Dominio.Professor;
import java.util.List;

import java.util.HashMap;
import regrasNegocio.IStrategy;

/**
 *
 * @author 55119
 */
public class GetTabela implements IStrategy {
    HashMap<EntidadeDominio,String> tabelas           = new HashMap<EntidadeDominio,String>(); 
    HashMap<String,List<String>> filtrosRequisicao = new HashMap<String,List<String>>(); 
    String baseSQL = "SELECT columns FROM tb_name {[OPT] [JOIN] [ON] {[PARAM]=[VALUE]}} WHERE {[PARAM] [LIKE||=] [VALUE]}";
    @Override
    public String processar(EntidadeDominio entidade) {
            if(entidade != null){
                tabelas.put(new Curso(),     "cursos");
                tabelas.put(new Materia(),   "materias");
                tabelas.put(new ItemGrade(), "grade_itens");
                tabelas.put(new GradeCurso(),"grade_curso");
                tabelas.put(new Pessoa(),    "pessoas");
                tabelas.put(new Aluno(),     "alunos");
                tabelas.put(new Professor(), "professores");
                tabelas.put(new Endereco(),  "enderecos");
            
                return tabelas.get(entidade.getClass());
            }else{
                return "Não é possível achar tabela de classe nula (validarConsulta)!";
            }
    }  
}
