/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regrasNegocio.implRegras;

import Dao.DAOAluno;
import Dao.DAOCurso;
import Dao.DAOPessoa;
import Dominio.Aluno;
import Dominio.Curso;
import Dominio.EntidadeDominio;
import Dominio.Pessoa;
import java.util.List;
import regrasNegocio.IStrategy;

/**
 *
 * @author 55119
 */
public class ValidarAluno implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {
        if(entidade==null){
                 return "Falha ao receber Aluno!";
        }
        if (entidade instanceof Aluno) {
            StringBuilder sb = new StringBuilder();

            Aluno aluno = (Aluno) entidade;
            Pessoa pessoa = (Pessoa) aluno;

            if(aluno.getId()!=0){
                DAOAluno DAOalu = new DAOAluno();
                List<EntidadeDominio> tempaluno = DAOalu.consultar(aluno);

                if(tempaluno==null){
                    sb.append("Erro, aluno não encontrado!");
                }
            }

            ValidarPessoa valPes = new ValidarPessoa();
            String msgPes = valPes.processar(aluno);

            if (msgPes != null) {
                sb.append(msgPes);
            }
            
            ValidarCurso valCur = new ValidarCurso();
            String msgCur = valCur.processar(aluno.getCurso());
            
            if(msgCur!=null){
                sb.append(msgCur);
            }else{
                DAOCurso DAOcur = new DAOCurso();
                Curso tempcurso = (Curso)DAOcur.consultar(aluno.getCurso()).get(0);
                
                aluno.setCurso(tempcurso);
            }
            
            if(aluno.getCurso()!=null&&aluno.getCurso().getId()>0){
                System.out.println(aluno.getCurso().getId());
                if(aluno.getSemestre()<0||aluno.getSemestre()>aluno.getCurso().getDuracao()){
                    sb.append("Semestre do aluno não é válido de acordo com o curso!");
                }
            }else{
                sb.append("Curso é obrigatório!");
            }

            if (sb.length() > 0) {
                return sb.toString();
            }
        } else {
            return "Entidade recebida Inválida, esperava Aluno";
        }
        return null;
    }
}
