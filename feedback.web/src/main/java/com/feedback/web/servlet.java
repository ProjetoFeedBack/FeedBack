package com.feedback.web;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feedback.core.aplicacao.Resultado;
import com.feedback.web.command.ICommand;
import com.feedback.web.command.impl.AlterarCommand;
import com.feedback.web.command.impl.ConsultarCommand;
import com.feedback.web.command.impl.ExcluirCommand;
import com.feedback.web.command.impl.SalvarCommand;
import com.feedback.web.command.impl.VisualizarCommand;
import com.feedback.web.vh.IViewHelper;
import com.feedback.web.vh.impl.GrupoViewHelper;
import com.feedback.web.vh.impl.UsuarioViewHelper;

import com.feedback.dominio.EntidadeDominio;


/**
 * servlet2 implementation class servlet2
 */
public class servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Map<String, ICommand> commands;
	private static Map<String, IViewHelper> vhs;
	
	
    /**
     * Default constructor. 
     */
    public servlet() {
    	
    	/* Utilizando o command para chamar a fachada e indexando cada command 
    	 * pela operacao garantimos que esta servelt atendera qualquer operacao */
    	commands = new HashMap<String, ICommand>();    	
    	
    	
    	commands.put("SALVAR", new SalvarCommand());
    	commands.put("EXCLUIR", new ExcluirCommand());
    	commands.put("CONSULTAR", new ConsultarCommand());
    	commands.put("VISUALIZAR", new VisualizarCommand());
    	commands.put("ALTERAR", new AlterarCommand());
    	
    	
    	/* Utilizando o ViewHelper para tratar especificacoes de qualquer tela e indexando 
    	 * cada viewhelper pela url em que esta servlet e chamada no form
    	 * garantimos que esta servelt atendera qualquer entidade */
    	
    	vhs = new HashMap<String, IViewHelper>();
    	/*A chave do mapa é o mapeamento da servlet2 para cada form que 
    	 * esta configurado no web.xml e sendo utilizada no action do html
    	 */
        
        vhs.put("/dinax-projeto-web/Usuario", new UsuarioViewHelper());
        vhs.put("/dinax-projeto-web/Grupo", new GrupoViewHelper());
    	
    }
    
    
    /** 
     * TODO Descri��o do M�todo
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
    		IOException {
    	doProcessRequest(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcessRequest(request, response);
	}
	
	
	protected void doProcessRequest(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
	
		//Obtem a uri que invocou esta servlet (O que foi definido no methdo do form html)
		String uri = request.getRequestURI();
		
		//Obt�m um viewhelper indexado pela uri que invocou esta servlet
		IViewHelper vh = vhs.get(uri);
		
		//O viewhelper retorna a entidade especifica para a tela que chamou esta servlet
		EntidadeDominio entidade =  vh.getEntidade(request);
		
		//Obt�m a opera��o executada
		String operacao = request.getParameter("operacao");
		
		//Obt�m o command para executar a respectiva opera��o
		ICommand command = commands.get(operacao);
				
		/*Executa o command que chamar� a fachada para executar a opera��o requisitada
		 * o retorno � uma inst�ncia da classe resultado que pode conter mensagens derro 
		 * ou entidades de retorno
		 */
		Resultado resultado = command.execute(entidade);
		
		/*
		 * Executa o m�todo setView do view helper espec�fico para definir como dever� ser apresentado 
		 * o resultado para o usu�rio
		 */
		vh.setView(resultado, request, response);
           
                
	
	}
}
