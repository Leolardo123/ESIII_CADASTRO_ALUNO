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
import Dominio.GradeCurso;
import Dominio.ItemGrade;
import Dominio.Materia;
import Dominio.Professor;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import regrasNegocio.implRegras.ValidarGrade;
import regrasNegocio.implRegras.ValidarItemGrade;

/**
 *
 * @author 55119
 */
public class VhGradeCurso implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        GradeCurso grade = new GradeCurso();
        ValidarGrade vG = new ValidarGrade();
        ValidarItemGrade vGI = new ValidarItemGrade();
        if ("SALVAR".equals(request.getParameter("operacao")) || "ALTERAR".equals(request.getParameter("operacao"))) {
            String[] materias = request.getParameterValues("materias[]");
            String[] professores = request.getParameterValues("professores[]");
            int semestre = Integer.parseInt(request.getParameter("semestre"));
            int curso_id = Integer.parseInt(request.getParameter("curso"));

            int extensao = vGI.turnos_validos.length * vGI.dias_validos.length * vGI.periodos_validos.length;
            int dia = 1;
            int periodo = 1;
            int turno = 1;
            int materia_id;
            int professor_id;
            boolean obrigatorio_val;

            ItemGrade item;

            List<ItemGrade> itens = new ArrayList<ItemGrade>();
            
            Curso curso = new Curso();
            curso.setId(curso_id);

            grade.setSemestre(semestre);

            for (int i = 0; i < professores.length; i++) {
                String tempmateria_id = materias[i];
                String tempprofessor_id = professores[i];

                if (!(tempmateria_id == null || tempmateria_id == null
                        || professores[i].equals("") || materias[i].equals(""))) {
                    materia_id = Integer.parseInt(tempmateria_id);
                    professor_id = Integer.parseInt(tempprofessor_id);
                    
                    if (materia_id > 0 && professor_id > 0) {

                        Materia materia = new Materia();
                        materia.setId(materia_id);

                        Professor professor = new Professor();
                        professor.setId(professor_id);

                        item = new ItemGrade(dia, turno, periodo, materia, professor);
                        itens.add(item);
                        System.out.println(i);
                    }
                }

                turno++;
                
                if (turno == vGI.turnos_validos.length) {
                    periodo++;
                    turno = 1;
                }
                if (periodo == vGI.periodos_validos.length) {
                    periodo = 1;
                    dia++;
                }
                if (dia == vGI.dias_validos.length) {
                    i = extensao;
                }
            }
            
            grade.setItens(itens);
            grade.setCurso_id(curso_id);

            if ("SALVAR".equals(request.getParameter("operacao"))) {
                return grade;
            }
        }

        if ("ALTERAR".equals(request.getParameter("operacao"))) {
            int id = Integer.parseInt(request.getParameter("id"));
            grade.setId(id);

            return grade;
        }

        if ("CONSULTAR".equals(request.getParameter("operacao"))) {
            return grade;
        }

        if ("CONSULTARID".equals(request.getParameter("operacao"))) {
            int id = Integer.parseInt(request.getParameter("id"));
            grade.setId(id);
            return grade;
        }

        if ("EXCLUIR".equals(request.getParameter("operacao"))) {
            int id = Integer.parseInt(request.getParameter("id"));
            grade.setId(id);
            return grade;
        }

        return null;
    }

    @Override
    public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response, EntidadeDominio entidade) throws ServletException {
        PrintWriter out;
        try {
            try {
                String msg = "";
                out = response.getWriter();
                if (resultado != null) {
                    if ("SALVAR".equals(request.getParameter("operacao"))) {
                        msg = "ERRO AO SALVAR: " + (String) resultado;
                        request.setAttribute("msg_error", msg);
                        request.getRequestDispatcher("./ListarGradeCurso?operacao=CONSULTAR").forward(request, response);
                    }
                    if ("ALTERAR".equals(request.getParameter("operacao"))) {
                        msg = "ERRO AO ALTERAR: " + (String) resultado;
                        request.setAttribute("msg_error", msg);
                        request.getRequestDispatcher("./ListarGradeCurso?operacao=CONSULTAR").forward(request, response);
                    }
                    if ("CONSULTAR".equals(request.getParameter("operacao"))) {
                        request.setAttribute("grades", resultado);
                        request.getRequestDispatcher("/grade.jsp").forward(request, response);
                    }
                    if ("CONSULTARID".equals(request.getParameter("operacao"))) {
                        request.setAttribute("grade", resultado);
                        request.getRequestDispatcher("/editar_grade.jsp").forward(request, response);
                    }
                } else {
                    if ("SALVAR".equals(request.getParameter("operacao"))) {
                        msg = "CADASTRADO COM SUCESSO";
                        request.setAttribute("msg_success", msg);
                        request.getRequestDispatcher("./ListarGradeCurso?operacao=CONSULTAR").forward(request, response);
                    }
                    if ("ALTERAR".equals(request.getParameter("operacao"))) {
                        msg = "ALTERADO COM SUCESSO";
                        request.setAttribute("msg_success", msg);
                        request.getRequestDispatcher("./ListarGradeCurso?operacao=CONSULTAR").forward(request, response);
                    }
                    if ("EXCLUIR".equals(request.getParameter("operacao"))) {
                        msg = "EXCLUIDO COM SUCESSO";
                        request.setAttribute("msg_success", msg);
                        request.getRequestDispatcher("./ListarGradeCurso?operacao=CONSULTAR").forward(request, response);
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
