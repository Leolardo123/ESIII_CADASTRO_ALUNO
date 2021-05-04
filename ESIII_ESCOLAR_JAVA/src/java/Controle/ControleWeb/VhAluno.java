/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.ControleWeb;

import Dominio.Aluno;
import Dominio.Endereco;
import Dominio.EntidadeDominio;
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
public class VhAluno implements IViewHelper{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        SimpleDateFormat date_format = new SimpleDateFormat();
        
        String primeiro_nome = request.getParameter("primeiro_nome");
        String ultimo_nome = request.getParameter("ultimo_nome");
        String rg = request.getParameter("rg");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String curso = request.getParameter("curso");
        Date data_nascimento = null;
        try {
            data_nascimento = date_format.parse(request.getParameter("data_nascimento"));
        } catch (ParseException ex) {
            Logger.getLogger(VhAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String logradouro = request.getParameter("txtCliente");
        int numero = Integer.parseInt(request.getParameter("numero"));
        String complemento = request.getParameter("complemento");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");
        String cep = request.getParameter("cep");//adicionar no formulário
        
        Endereco endereco = new Endereco(cep, estado, cidade, numero, logradouro);
        
        Aluno aluno = new Aluno("'AL001", rg, cpf, primeiro_nome, ultimo_nome,
                email, data_nascimento, endereco, 1);
        return aluno;
    }

    @Override
    public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response) throws ServletException {
        PrintWriter out;
        try {
                out = response.getWriter();
                if (resultado != null) {
                        out.println(resultado);
                } else {
                        out.println("<h1>Cliente cadastrado!</h1>");
                }
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
    }
    
}
