/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControleWeb;

import Dominio.Aluno;
import Dominio.Curso;
import Dominio.Endereco;
import Dominio.EntidadeDominio;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eu
 */
public class VhAluno implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        try {
            Aluno aluno = new Aluno();
            Endereco endereco = new Endereco();
            if ("SALVAR".equals(request.getParameter("operacao"))||"ALTERAR".equals(request.getParameter("operacao"))) {
                SimpleDateFormat date_format = new SimpleDateFormat("dd-MM-yyyy");

                String primeiro_nome = request.getParameter("primeiro_nome");
                String ultimo_nome = request.getParameter("ultimo_nome");
                String rg = request.getParameter("rg");
                String cpf = request.getParameter("cpf");
                String email = request.getParameter("email");
                int curso_id = Integer.parseInt(request.getParameter("curso"));
                Date data_nascimento = date_format.parse(request.getParameter("data_nascimento"));

                String logradouro = request.getParameter("logradouro");
                int numero = Integer.parseInt(request.getParameter("numero"));
                String complemento = request.getParameter("complemento");
                String cidade = request.getParameter("cidade");
                String estado = request.getParameter("estado");
                String cep = request.getParameter("cep");

                endereco.setCep(cep);
                endereco.setEstado(estado);
                endereco.setCidade(cidade);
                endereco.setNumero(numero);
                endereco.setLogradouro(logradouro);

                if (complemento != null) {
                    endereco.setComplemento(complemento);
                }
                
                Curso curso = new Curso();
                curso.setId(curso_id);

                aluno.setRg(rg);
                aluno.setCpf(cpf);
                aluno.setPnome(primeiro_nome);
                aluno.setUnome(ultimo_nome);
                aluno.setEmail(email);
                aluno.setDtNascimento(data_nascimento);
                aluno.setEndereco(endereco);
                aluno.setSemestre(1);
                aluno.setCurso(curso);
                
                if ("ALTERAR".equals(request.getParameter("operacao"))) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    aluno.setId(id);
                }
                
                return aluno;
            }else
            if ("CONSULTAR".equals(request.getParameter("operacao"))) {
                return aluno;
            }else
            if ("CONSULTARID".equals(request.getParameter("operacao"))) {
                int id = Integer.parseInt(request.getParameter("id"));
                aluno.setId(id);
                return aluno;
            }else
            if ("EXCLUIR".equals(request.getParameter("operacao"))) {
                int id = Integer.parseInt(request.getParameter("id"));
                aluno.setId(id);
                return aluno;
            }
            return null;

        } catch (ParseException ex) {
            return null;
        }
    }

    @Override
    public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response, EntidadeDominio entidade) throws ServletException {
        PrintWriter out;
        try {
            String msg = "";
            out = response.getWriter();
            if (resultado != null) {
                if ("SALVAR".equals(request.getParameter("operacao"))) {
                    msg = "ERRO NO CADASTRO: "+(String)resultado;
                    request.setAttribute("msg_error", msg);
                    request.getRequestDispatcher("./ListarAluno?operacao=CONSULTAR").forward(request, response);
                }
                if ("ALTERAR".equals(request.getParameter("operacao"))) {
                    msg = "ERRO AO ALTERAR: "+(String)resultado;
                    request.setAttribute("msg_error", msg);
                    request.getRequestDispatcher("./ListarAluno?operacao=CONSULTAR").forward(request, response);
                }
                if ("CONSULTAR".equals(request.getParameter("operacao"))) {
                    request.setAttribute("alunos", resultado);
                    request.getRequestDispatcher("/aluno.jsp").forward(request, response);
                }
                if ("CONSULTARID".equals(request.getParameter("operacao"))) {
                    request.setAttribute("aluno", resultado);
                    request.getRequestDispatcher("/editar_aluno.jsp").forward(request, response);
                }
            } else {
                if ("SALVAR".equals(request.getParameter("operacao"))) {
                    msg = "CADASTRADO COM SUCESSO";
                    request.setAttribute("msg_success", msg);
                    request.getRequestDispatcher("./ListarAluno?operacao=CONSULTAR").forward(request, response);
                }
                if ("ALTERAR".equals(request.getParameter("operacao"))) {
                    msg = "ALTERADO COM SUCESSO";
                    request.setAttribute("msg_success", msg);
                    request.getRequestDispatcher("./ListarAluno?operacao=CONSULTAR").forward(request, response);
                }
                if ("EXCLUIR".equals(request.getParameter("operacao"))) {
                    msg = "EXCLUIDO COM SUCESSO";
                    request.setAttribute("msg_success", msg);
                    request.getRequestDispatcher("./ListarAluno?operacao=CONSULTAR").forward(request, response);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
