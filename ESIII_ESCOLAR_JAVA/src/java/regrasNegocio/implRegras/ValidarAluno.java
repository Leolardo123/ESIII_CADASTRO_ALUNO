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
        if (entidade instanceof Aluno) {
            StringBuilder sb = new StringBuilder();

            Aluno aluno = (Aluno) entidade;
            Pessoa pessoa = (Pessoa) aluno;

            if (aluno.getId() != 0) {
                DAOAluno DAOalu = new DAOAluno();
                List<EntidadeDominio> tempaluno = DAOalu.consultar(aluno);

                if (tempaluno != null) {
                    aluno = (Aluno) tempaluno.get(0);
                }
            }

            ValidarPessoa valPes = new ValidarPessoa();
            String msgPes = valPes.processar(aluno);

            if (msgPes != null) {
                sb.append(msgPes);
            }

            DAOCurso DAOcur = new DAOCurso();
            Curso curso = (Curso)DAOcur.consultar(aluno.getCurso()).get(0);
            
            ValidarCurso valCur = new ValidarCurso();
            String msgCur = valCur.processar(aluno.getCurso());
            
            if (aluno.getCurso() == null||aluno.getCurso().getId()==0) {
                return "Curso não encontrado!";
            }
            
            aluno.setCurso(curso);
            
            if(aluno.getSemestre()<0||aluno.getSemestre()>aluno.getCurso().getDuracao()){
                System.out.println(aluno.getSemestre()+":"+aluno.getCurso().getDuracao());
                sb.append("Semestre do aluno não é válido de acordo com o curso!");
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
