/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.ControleWeb;

import Controle.*;
import Dominio.EntidadeDominio;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eu
 */
public class Controle {

    private static final long serialVersionUID = 1L;
    private static String operacao = null;
    private static Map<String, ICommand> commands;
    private static Map<String, IViewHelper> vhs;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controle() {
        super();
        commands = new HashMap<String, ICommand>();
        commands.put("SALVAR", new SalvarCommand());
        commands.put("EXCLUIR", new ExcluirCommand());
        commands.put("CONSULTAR", new ConsultarCommand());
        commands.put("ALTERAR", new AlterarCommand());

        vhs = new HashMap<String, IViewHelper>();
        /*
		 * A chave do mapa � o mapeamento da servlet para cada form que est� configurado
		 * no web.xml e sendo utilizada no action do html
         */
        vhs.put("/ProjetoESIII_Cliente/SalvarCliente", new VhAluno());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        operacao = request.getParameter("operacao");

        String uri = request.getRequestURI();

        IViewHelper vh = vhs.get(uri);
        EntidadeDominio entidade = vh.getEntidade(request);

        ICommand cmd = commands.get(operacao);
        
        if(entidade != null){
            Object msg = cmd.execute(entidade);
            vh.setView(msg, request, response);
        }
    }

}
