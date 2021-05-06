/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.ControleWeb;

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
public class VhProfessor implements IViewHelper{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        try {
            SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");

            String primeiro_nome = request.getParameter("primeiro_nome");
            String ultimo_nome = request.getParameter("ultimo_nome");
            Date data_nascimento = date_format.parse(request.getParameter("data_nascimento"));
            String rg = request.getParameter("rg");
            String cpf = request.getParameter("cpf");
            String email = request.getParameter("email");
            float salario = Float.parseFloat(request.getParameter("salario"));


            String logradouro = request.getParameter("logradouro");
            int numero = Integer.parseInt(request.getParameter("numero"));
            String complemento = request.getParameter("complemento");
            String cidade = request.getParameter("cidade");
            String estado = request.getParameter("estado");
            String cep = request.getParameter("cep");

            Endereco endereco = new Endereco(cep, estado, cidade, numero, logradouro);
            
            if(complemento != null) endereco.setCep(cep);

            Professor professor = new Professor(salario, rg, cpf, primeiro_nome, ultimo_nome,
                    email, data_nascimento, endereco);
            return professor;
        } catch (ParseException ex) {
            return null;
        }
    }

    @Override
    public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException {
        PrintWriter out;
        try {
                out = response.getWriter();
                if (resultado != null) {
                        out.println(resultado);
                } else {
                        out.println("<h1>Professor cadastrado!</h1>");
                }
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
    }
    
}
