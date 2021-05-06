/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.ControleWeb;

import Dominio.Curso;
import Dominio.Endereco;
import Dominio.EntidadeDominio;
import Dominio.Pessoa;
import Dominio.Professor;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eu
 */
public class VhCurso implements IViewHelper{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {

            String nome_curso = request.getParameter("nome_curso");
            int duracao = Integer.parseInt(request.getParameter("duracao"));
            Double mensalidade = Double.parseDouble(request.getParameter("mensalidade"));
            String nivel = request.getParameter("nivel");
            String descricao = request.getParameter("descricao");
            
            Curso curso = new Curso(nome_curso, descricao, nivel, duracao, mensalidade);
            return curso;
    }

    @Override
    public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException {
        PrintWriter out;
        try {
                out = response.getWriter();
                if (resultado != null) {
                        out.println(resultado);
                } else {
                        out.println("<h1>Curso cadastrado!</h1>");
                }
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
    }
    
}
