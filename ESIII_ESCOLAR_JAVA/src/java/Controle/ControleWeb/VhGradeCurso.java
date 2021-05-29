/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.ControleWeb;

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
public class VhGradeCurso implements IViewHelper{
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        ValidarGrade vG = new ValidarGrade();
        ValidarItemGrade vGI = new ValidarItemGrade();

        String[] materias = request.getParameterValues("materias");
        String[] professores = request.getParameterValues("professores");
        String[] obrigatorio = request.getParameterValues("obrigatorio");
        int semestre = Integer.parseInt(request.getParameter("semestre"));
        int curso_id = Integer.parseInt(request.getParameter("curso"));

        int extensao = vGI.turnos_validos.length * vGI.dias_validos.length * vGI.periodos_validos.length;
        int dia = 1;
        int periodo = 1;
        int turno = 1;
        int materia_id;
        int professor_id;
        boolean obrigatorio_val;
        
        ItemGrade itemGrade;
        GradeCurso grade = new GradeCurso();
        List<GradeCurso> gradeList = new ArrayList<GradeCurso>();
        
        Curso curso = new Curso();
        curso.setId(curso_id);
        
        grade.setCurso(curso);
        grade.setSemestre(semestre);

        for (int i = 0; i < extensao; i++) {
            materia_id = Integer.parseInt(materias[i]);
            professor_id = Integer.parseInt(professores[i]);
            obrigatorio_val = Boolean.parseBoolean(obrigatorio[i]);
            
            Materia materia = new Materia();
            materia.setId(materia_id);
            
            Professor professor = new Professor();
            professor.setId(professor_id);
            
            itemGrade = new ItemGrade(obrigatorio_val, dia, turno, periodo, materia, professor);
            grade.AddItem(itemGrade);

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
        String MsgValGrade =  vG.processar(grade);

        return grade;
    }

    @Override
    public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException {
        PrintWriter out;
        try {
                out = response.getWriter();
                if (resultado != null) {
                        out.println(resultado);
                } else {
                        out.println("<h1>Aluno cadastrado!</h1>");
                }
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
    }
}
