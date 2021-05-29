/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regrasNegocio.implRegras;

import regrasNegocio.IStrategy;
import Dominio.Aluno;
import Dominio.Curso;
import Dominio.Endereco;
import Dominio.EntidadeDominio;
import Dominio.GradeCurso;
import Dominio.ItemGrade;
import Dominio.Materia;
import Dominio.Pessoa;
import Dominio.Professor;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author 55119
 */
public class ValidarColunas implements IStrategy {
    HashMap<String,List<String>> filtrosRequisicao = new HashMap<String,List<String>>(); 
    @Override
    public String processar(EntidadeDominio entidade) {


        if (entidade != null) {
            

        } else {
            return "Não é possível achar tabela de classe nula (validarConsulta)!";
        }
        return null;
    }
}
