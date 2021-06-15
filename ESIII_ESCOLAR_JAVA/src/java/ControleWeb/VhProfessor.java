/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControleWeb;

import Dominio.Curso;
import Dominio.Endereco;
import Dominio.EntidadeDominio;
import Dominio.Professor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eu
 */
public class VhProfessor implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        try {
            Professor professor = new Professor();
            Endereco endereco = new Endereco();
            if ("SALVAR".equals(request.getParameter("operacao"))||"ALTERAR".equals(request.getParameter("operacao"))) {
                SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");

                String primeiro_nome = request.getParameter("primeiro_nome");
                String ultimo_nome = request.getParameter("ultimo_nome");
                String rg = request.getParameter("rg");
                String cpf = request.getParameter("cpf");
                String email = request.getParameter("email");
                Date data_nascimento = date_format.parse(request.getParameter("data_nascimento"));
                double salario = (Math.round(Double.parseDouble((request.getParameter("salario")))*100))/100d;

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

                professor.setRg(rg);
                professor.setCpf(cpf);
                professor.setPnome(primeiro_nome);
                professor.setUnome(ultimo_nome);
                professor.setEmail(email);
                professor.setDtNascimento(data_nascimento);
                professor.setSalario(salario);
                
                if ("ALTERAR".equals(request.getParameter("operacao"))) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    int end_id = Integer.parseInt(request.getParameter("endereco_id"));
                    endereco.setId(end_id);
                    professor.setId(id);
                }
                professor.setEndereco(endereco);
                return professor;
            }
            if ("CONSULTAR".equals(request.getParameter("operacao"))) {
                return professor;
            }
            if ("CONSULTARID".equals(request.getParameter("operacao"))) {
                int id = Integer.parseInt(request.getParameter("id"));
                professor.setId(id);
                return professor;
            }
            if ("EXCLUIR".equals(request.getParameter("operacao"))) {
                int id = Integer.parseInt(request.getParameter("id"));
                int endid = Integer.parseInt(request.getParameter("endid"));
                professor.setId(id);
                endereco.setId(endid);
                professor.setEndereco(endereco);
                return professor;
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
                    msg = "ERRO NO CADASTRO: " + (String) resultado;
                    request.setAttribute("msg_error", msg);
                    request.getRequestDispatcher("./professor.jsp").forward(request, response);
                }
                if ("ALTERAR".equals(request.getParameter("operacao"))) {
                    msg = "ERRO AO ALTERAR: " + (String) resultado;
                    request.setAttribute("msg_error", msg);
                    request.getRequestDispatcher("./professor.jsp").forward(request, response);
                }
                if ("CONSULTAR".equals(request.getParameter("operacao"))) {
                    Gson gson = new GsonBuilder().create();
                    Type type = new TypeToken<List<Professor>>(){}.getType();
                    String json = gson.toJson(resultado, type);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                }
                if ("CONSULTARID".equals(request.getParameter("operacao"))) {
                    request.setAttribute("professor", resultado);
                    request.getRequestDispatcher("/editar_professor.jsp").forward(request, response);
                }
            } else {
                if ("SALVAR".equals(request.getParameter("operacao"))) {
                    msg = "CADASTRADO COM SUCESSO";
                    request.setAttribute("msg_success", msg);
                    request.getRequestDispatcher("./professor.jsp").forward(request, response);
                }
                if ("ALTERAR".equals(request.getParameter("operacao"))) {
                    msg = "ALTERADO COM SUCESSO";
                    request.setAttribute("msg_success", msg);
                    request.getRequestDispatcher("./professor.jsp").forward(request, response);
                }
                if ("EXCLUIR".equals(request.getParameter("operacao"))) {
                    msg = "EXCLUIDO COM SUCESSO";
                    request.setAttribute("msg_success", msg);
                    request.getRequestDispatcher("./professor.jsp").forward(request, response);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
