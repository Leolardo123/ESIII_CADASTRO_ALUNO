/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControleWeb;

import Dominio.Aluno;
import Dominio.Curso;
import Dominio.EntidadeDominio;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import regrasNegocio.implRegras.ValidarCurso;

/**
 *
 * @author Eu
 */
public class VhCurso implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {

        Curso curso = new Curso();

        if ("SALVAR".equals(request.getParameter("operacao"))||"ALTERAR".equals(request.getParameter("operacao"))) {
            String nome_curso = request.getParameter("nome_curso");
            curso.setNome(nome_curso);
            int duracao = Integer.parseInt(request.getParameter("duracao"));
            curso.setDuracao(duracao);
            Double mensalidade = Double.parseDouble(request.getParameter("mensalidade"));
            curso.setMensalidade(mensalidade);
            String nivel = request.getParameter("nivel");
            curso.setNivel(nivel);
            String descricao = request.getParameter("descricao");
            curso.setDescricao(descricao);
            
            if("SALVAR".equals(request.getParameter("operacao")))return curso;
        }
        if ("ALTERAR".equals(request.getParameter("operacao"))) {
            int id = Integer.parseInt(request.getParameter("id"));
            curso.setId(id);

            return curso;
        }
        if ("CONSULTAR".equals(request.getParameter("operacao"))) {
            return curso;
        }
        if ("CONSULTARID".equals(request.getParameter("operacao"))) {
            int id = Integer.parseInt(request.getParameter("id"));
            curso.setId(id);
            return curso;
        }
        if ("EXCLUIR".equals(request.getParameter("operacao"))) {
            int id = Integer.parseInt(request.getParameter("id"));
            curso.setId(id);
            return curso;
        }

        return null;

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
                    request.getRequestDispatcher("./curso.jsp").forward(request, response);
                }
                if ("ALTERAR".equals(request.getParameter("operacao"))) {
                    msg = "ERRO AO ALTERAR: "+(String)resultado;
                    request.setAttribute("msg_error", msg);
                    request.getRequestDispatcher("./curso.jsp").forward(request, response);
                }
                if("CONSULTAR".equals(request.getParameter("operacao"))){
                    Gson gson = new GsonBuilder().create();
                    Type type = new TypeToken<List<Curso>>(){}.getType();
                    String json = gson.toJson(resultado, type);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                }
                if("CONSULTARID".equals(request.getParameter("operacao"))){
                    request.setAttribute("curso", resultado);
                    request.getRequestDispatcher("/editar_curso.jsp").forward(request, response);
                }
                if("GETNIVEIS".equals(request.getParameter("operacao"))){
                    ValidarCurso valCur = new ValidarCurso();
                    String niveis[] = valCur.niveis;
                    Gson gson = new GsonBuilder().create();
                    Type type = new TypeToken<List<Curso>>(){}.getType();
                    String json = gson.toJson(niveis, type);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                }
            } else {
                if ("SALVAR".equals(request.getParameter("operacao"))) {
                    msg = "CADASTRADO COM SUCESSO";
                    request.setAttribute("msg_success", msg);
                    request.getRequestDispatcher("./curso.jsp").forward(request, response);
                }
                if ("ALTERAR".equals(request.getParameter("operacao"))) {
                    msg = "ALTERADO COM SUCESSO";
                    request.setAttribute("msg_success", msg);
                    request.getRequestDispatcher("./curso.jsp").forward(request, response);
                }
                if ("EXCLUIR".equals(request.getParameter("operacao"))) {
                    msg = "EXCLUIDO COM SUCESSO";
                    request.setAttribute("msg_success", msg);
                    request.getRequestDispatcher("./curso.jsp").forward(request, response);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
