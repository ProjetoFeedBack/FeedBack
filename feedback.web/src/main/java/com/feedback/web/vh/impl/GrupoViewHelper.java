
package com.feedback.web.vh.impl;

import com.feedback.core.aplicacao.Resultado;
import com.feedback.core.util.ConverteDate;
import com.feedback.dominio.EntidadeDominio;
import com.feedback.dominio.Grupo;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.feedback.web.vh.IViewHelper;




public class GrupoViewHelper implements IViewHelper {

	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		String operacao = request.getParameter("operacao");
		Grupo grupo = null; 
		
		if(!operacao.equals("VISUALIZAR")){
                    
                        String id = request.getParameter("txtId");
                        String nome = request.getParameter("txtNome");
                        String descricao = request.getParameter("txtDescricao");
                        String nivel = request.getParameter("txtNivel");
                        String nivel_grupopai = request.getParameter("txtNivel_GrupoPai"); 
                        
                        String dtCadastro = request.getParameter("txtDtCadastro");
                        
			grupo = new Grupo();
			
                        		
			if(nome != null && !nome.trim().equals("")){
				grupo.setNome(nome);
			}
			
			if(id != null && !id.trim().equals("")){
				grupo.setId(Integer.parseInt(id));
			}
			
			if(descricao != null && !descricao.trim().equals("")){
				grupo.setDescricao(descricao);
			}
                        
                        if(nivel != null && !nivel.trim().equals("")){
				grupo.setNivel(nivel);
			}
                        
                        if(nivel_grupopai != null && !nivel_grupopai.trim().equals("")){
				grupo.setNivel_grupopai(nivel_grupopai);
			}
			
			if(dtCadastro != null && !dtCadastro.trim().equals("")){
				grupo.setDtCadastro(ConverteDate.converteStringDate(dtCadastro));
			}
                        
                        
		}else{
			HttpSession session = request.getSession();
			Resultado resultado = (Resultado) session.getAttribute("resultado");
			String txtId = request.getParameter("txtId");
			int id=0;
			
			if(txtId != null && !txtId.trim().equals("")){
				id = Integer.parseInt(txtId);
			}
			
			for(EntidadeDominio e: resultado.getEntidades()){
				if(e.getId() == id){
					grupo = (Grupo)e;
				}
			}
		}
		
		return grupo;
	}

	
	public void setView(Resultado resultado, HttpServletRequest request, 
			HttpServletResponse response)  throws IOException, ServletException {
		RequestDispatcher d=null;
		
		String operacao = request.getParameter("operacao");
		
		if(resultado.getMsg() == null){
			if(operacao.equals("SALVAR")){
				resultado.setMsg("Grupo cadastrado com sucesso!");
			}
			
                        if(operacao.equals("CONSULTAR")){
				request.setAttribute("resultado", resultado);
 				d= request.getRequestDispatcher("FormListaGrupo.jsp"); 
                                d.forward(request,response);
			}
                        
			request.setAttribute("resultado", resultado);
                       	d= request.getRequestDispatcher("FormListaGrupo.jsp");  
                        
						
		}
		
		if(resultado.getMsg() == null && operacao.equals("ALTERAR")){
			
			d= request.getRequestDispatcher("FormGrupo.jsp");  
		}
		
		if(resultado.getMsg() == null && operacao.equals("VISUALIZAR")){
			
			request.setAttribute("usuario", resultado.getEntidades().get(0));
			d= request.getRequestDispatcher("FormGrupo.jsp");  			
		}
		
		if(resultado.getMsg() == null && operacao.equals("EXCLUIR")){
			
			request.getSession().setAttribute("resultado", null);
			d= request.getRequestDispatcher("FormListaGrupo.jsp");  
		}
		
		if(resultado.getMsg() != null){
			if(operacao.equals("SALVAR") || operacao.equals("ALTERAR")){
				request.getSession().setAttribute("resultado", resultado);
 				d= request.getRequestDispatcher("FormGrupo.jsp");  	
			}
                        if(operacao.equals("CONSULTAR")){
				request.getSession().setAttribute("resultado", resultado);
 				d= request.getRequestDispatcher("FormListaGrupo.jsp");  	
			}
		}
		
		d.forward(request,response); 
		
	}

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setView(EntidadeDominio entidade, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
