/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.ControleWeb;

import Dominio.EntidadeDominio;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
        String primeiro_nome = request.getParameter("primeiro_nome");
        String ultimo_nome = request.getParameter("ultimo_nome");
        String rg = request.getParameter("rg");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String curso = request.getParameter("curso");
        String logradouro = request.getParameter("txtCliente");
        String numero = request.getParameter("numero");
        String complemento = request.getParameter("complemento");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");

        Estado estado = new Estado(primeiro_nome);
        Cidade cidade = new Cidade(ultimo_nome, estado);

        Endereco endereco = new Endereco(rg, email, cpf, cidade);
        endereco.setDtCadastro(new Date());

        String txtDep1 = request.getParameter("txtDep1");
        String txtDep2 = request.getParameter("txtDep2");

        String txtParentesco1 = request.getParameter("txtParentesco1");
        String txtParentesco2 = request.getParameter("txtParentesco2");

        Parentesco par1 = new Parentesco(txtParentesco1);
        Parentesco par2 = new Parentesco(txtParentesco2);

        Dependente dep1 = new Dependente(txtDep1, par1);
        Dependente dep2 = new Dependente(txtDep2, par2);

        Cliente cliente = new Cliente(logradouro, curso, numero, complemento, endereco, dep1, dep2);
        return cliente;
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
