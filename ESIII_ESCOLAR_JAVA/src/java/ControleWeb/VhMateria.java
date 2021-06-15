/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControleWeb;

import Dao.DAODependentes;
import Dominio.Aluno;
import Dominio.Materia;
import Dominio.EntidadeDominio;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Eu
 */
public class VhMateria implements IViewHelper {
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        try {
            Materia materia = new Materia();

            if ("SALVAR".equals(request.getParameter("operacao")) || "ALTERAR".equals(request.getParameter("operacao"))) {
                String nome_materia = request.getParameter("nome_materia");
                materia.setNome(nome_materia);
                int carga_horaria = Integer.parseInt(request.getParameter("carga_horaria"));
                materia.setCarga_horaria(carga_horaria);
                String descricao = request.getParameter("descricao");
                materia.setDescricao(descricao);

                String[] dep_ids = request.getParameterValues("dependencia[]");
                Materia dep;

                if(dep_ids!=null){
                    List<Materia> deps = new ArrayList<Materia>();
                    for (String id : dep_ids) {
                        dep = new Materia();
                        dep.setId(Integer.parseInt(id));
                        deps.add(dep);
                    }
                    materia.setDependencias(deps);
                }

                if ("SALVAR".equals(request.getParameter("operacao"))) {
                    return materia;
                }
            }
            if ("ALTERAR".equals(request.getParameter("operacao"))) {
                int id = Integer.parseInt(request.getParameter("id"));
                materia.setId(id);

                return materia;
            }
            if ("CONSULTAR".equals(request.getParameter("operacao"))) {
                return materia;
            }
            if ("CONSULTARID".equals(request.getParameter("operacao"))) {
                int id = Integer.parseInt(request.getParameter("id"));
                materia.setId(id);
                return materia;
            }
            if ("CONSULTARDEP".equals(request.getParameter("operacao"))) {
                return materia;
            }
            if ("CONSULTARTODASDEP".equals(request.getParameter("operacao"))) {
                return materia;
            }
            if ("EXCLUIR".equals(request.getParameter("operacao"))) {
                int id = Integer.parseInt(request.getParameter("id"));
                materia.setId(id);
                return materia;
            }
        } catch (NullPointerException e) {
            throw e;
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
                    msg = "ERRO NO CADASTRO: " + (String) resultado;
                    request.setAttribute("msg_error", msg);
                    request.getRequestDispatcher("/materia.jsp").forward(request, response);
                }
                if ("ALTERAR".equals(request.getParameter("operacao"))) {
                    msg = "ERRO AO ALTERAR: " + (String) resultado;
                    request.setAttribute("msg_error", msg);
                    request.getRequestDispatcher("/materia.jsp").forward(request, response);
                }
                if ("CONSULTAR".equals(request.getParameter("operacao"))) {
                    Gson gson = new GsonBuilder().create();
                    Type type = new TypeToken<List<Materia>>(){}.getType();
                    String json = gson.toJson(resultado, type);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                }
                if ("CONSULTARID".equals(request.getParameter("operacao"))) {
                    request.setAttribute("materia", resultado);
                    request.getRequestDispatcher("/editar_materia.jsp").forward(request, response);
                }
                if ("CONSULTARTODASDEP".equals(request.getParameter("operacao"))) {
                    Gson gson = new GsonBuilder().create();
                    Type type = new TypeToken<List<Materia>>(){}.getType();
                    String json = gson.toJson(resultado, type);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                }
            } else {
                if ("SALVAR".equals(request.getParameter("operacao"))) {
                    msg = "CADASTRADO COM SUCESSO";
                    request.setAttribute("msg_success", msg);
                    request.getRequestDispatcher("/materia.jsp").forward(request, response);
                }
                if ("ALTERAR".equals(request.getParameter("operacao"))) {
                    msg = "ALTERADO COM SUCESSO";
                    request.setAttribute("msg_success", msg);
                    request.getRequestDispatcher("/materia.jsp").forward(request, response);
                }
                if ("EXCLUIR".equals(request.getParameter("operacao"))) {
                    msg = "EXCLUIDO COM SUCESSO";
                    request.setAttribute("msg_success", msg);
                    request.getRequestDispatcher("/materia.jsp").forward(request, response);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
